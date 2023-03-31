package com.teddyguy.meetupapp.dto.request;

import java.util.Date;

public record MeetUpEventSaveRequest(Long id, String name, Date date) {
}
