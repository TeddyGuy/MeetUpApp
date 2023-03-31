package com.teddyguy.meetupapp.dto.response;

import com.teddyguy.meetupapp.model.MeetUpEvent;
import com.teddyguy.meetupapp.model.MeetUpUser;

import java.util.Date;
import java.util.List;

public record MeetUpEventFetchResponse(Long id, String name, Date date, Integer numberOfConfirmedAttendees,Long organizerId, String organizerName) {
    public static MeetUpEventFetchResponse from(MeetUpEvent event){
        return new MeetUpEventFetchResponse(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getConfirmedAttendees().size(),
                event.getOrganizer().getId(),
                event.getOrganizer().getName()
        );
    }
}
