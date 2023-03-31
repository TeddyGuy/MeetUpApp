package com.teddyguy.meetupapp.controller;

import com.teddyguy.meetupapp.dto.request.MeetUpEventSaveRequest;
import com.teddyguy.meetupapp.dto.response.MeetUpEventFetchResponse;
import com.teddyguy.meetupapp.dto.response.MeetUpEventSaveResponse;
import com.teddyguy.meetupapp.service.MeetUpEventService;
import com.teddyguy.meetupapp.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/events")
public class MeetUpEventController {
    MeetUpEventService meetUpEventService;
    JwtUtils jwtUtils;
    @PostMapping
    public ResponseEntity<MeetUpEventSaveResponse> save(
            @RequestBody MeetUpEventSaveRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String header
    )
    {
        String jwt = header.substring(7);
        String username = jwtUtils.getUsernameFromToken(jwt);
        return ResponseEntity.ok(meetUpEventService.save(username, request));
    }

    @GetMapping
    public ResponseEntity<List<MeetUpEventFetchResponse>> search(@RequestParam(required = false, name="query") String query){
        return ResponseEntity.ok(meetUpEventService.search(query));
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<Void> join(
            @PathVariable(name = "id") Long eventId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String header
    )
    {
        String jwt = header.substring(7);
        String username = jwtUtils.getUsernameFromToken(jwt);
        meetUpEventService.join(eventId, username);
        return ResponseEntity.ok().build();
    }
}
