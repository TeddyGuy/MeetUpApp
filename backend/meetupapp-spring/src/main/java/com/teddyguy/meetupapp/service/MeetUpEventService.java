package com.teddyguy.meetupapp.service;

import com.teddyguy.meetupapp.dto.request.MeetUpEventSaveRequest;
import com.teddyguy.meetupapp.dto.response.MeetUpEventFetchResponse;
import com.teddyguy.meetupapp.dto.response.MeetUpEventSaveResponse;

import java.util.List;

public interface MeetUpEventService {
    MeetUpEventSaveResponse save(String organizerUsername, MeetUpEventSaveRequest request);
    void join(Long meetUpEventId, String username);

    //TODO add string search, its just a global fetch for now
    List<MeetUpEventFetchResponse> search(String queryString);
}
