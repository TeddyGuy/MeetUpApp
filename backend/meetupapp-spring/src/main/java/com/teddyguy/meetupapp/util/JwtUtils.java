package com.teddyguy.meetupapp.util;

import com.teddyguy.meetupapp.model.MeetUpUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    private static final int TOKEN_EXPIRATION_TIME_MILLIS = 1000 * 60 * 60 * 24; // 1 day
    private static final String SECRET = "2D4B6150645367566B59703373367638792F423F4528482B4D6251655468576D";

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(getSignedKey()).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, user.getUsername());
    }

    public String generateTokenMeetUpUser(MeetUpUser user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name",user.getName());
        return doGenerateToken(claims, user.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME_MILLIS))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }

    public Boolean isTokenExpiredForTokenExpirationTime(String token){
        return getExpirationDateFromToken(token).toInstant().isBefore(Instant.now().minusMillis(TOKEN_EXPIRATION_TIME_MILLIS));
    }

    //validate token
    public Boolean validateToken(String token, UserDetails user, HttpServletRequest request) {
        final String username = getUsernameFromToken(token);
        return (username.equals(
                user.getUsername()) &&
                (!isTokenExpired(token)
                        ||
                        request.getRequestURI().equals("/refresh") && !isTokenExpiredForTokenExpirationTime(token)));
    }

    private Key getSignedKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
