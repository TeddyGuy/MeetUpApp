package com.teddyguy.meetupapp.dto.response;

import com.teddyguy.meetupapp.model.MeetUpEvent;

import java.util.Date;

public record MeetUpEventSaveResponse(Long id, String name, Date date, Long organizerId) {
    public static MeetUpEventSaveResponse from(MeetUpEvent event){
        return new MeetUpEventSaveResponse(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getOrganizer().getId()
        );
    }
}
