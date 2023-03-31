package com.teddyguy.meetupapp.service;

import com.teddyguy.meetupapp.dto.response.MeetUpUserFetchResponse;

import java.util.List;

public interface MeetUpUserService {
    List<MeetUpUserFetchResponse> search(String queryString);
}
