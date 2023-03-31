package com.teddyguy.meetupapp.dto.response;

import com.teddyguy.meetupapp.model.MeetUpUser;

public record MeetUpUserFetchResponse(String name, Integer numberOfOrganizedEvents) {

    public static MeetUpUserFetchResponse from(MeetUpUser user) {
        return new MeetUpUserFetchResponse(user.getName(), user.getOrganizedEvents().size());
    }
}
