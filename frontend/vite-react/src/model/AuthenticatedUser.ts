export default interface DecodedJwt {
    name: string;
    sub: string;
    iat: number; //issued at
    exp: number; //expiring at
}
