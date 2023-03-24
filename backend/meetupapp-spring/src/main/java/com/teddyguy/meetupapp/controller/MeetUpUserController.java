package com.teddyguy.meetupapp.controller;

import com.teddyguy.meetupapp.dto.response.MeetUpUserFetchResponse;
import com.teddyguy.meetupapp.service.MeetUpUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/users")
public class MeetUpUserRepository {
    MeetUpUserService meetUpUserService;

    @GetMapping
    public ResponseEntity<List<MeetUpUserFetchResponse>> search(@RequestParam String name){
        return ResponseEntity.ok(
                meetUpUserService.search(name)
        );
    }
}
