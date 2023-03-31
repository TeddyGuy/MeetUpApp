package com.teddyguy.meetupapp.service.impl;

import com.teddyguy.meetupapp.dto.request.MeetUpEventSaveRequest;
import com.teddyguy.meetupapp.dto.response.MeetUpEventFetchResponse;
import com.teddyguy.meetupapp.dto.response.MeetUpEventSaveResponse;
import com.teddyguy.meetupapp.model.MeetUpEvent;
import com.teddyguy.meetupapp.model.MeetUpUser;
import com.teddyguy.meetupapp.repository.MeetUpEventRepository;
import com.teddyguy.meetupapp.repository.MeetUpUserRepository;
import com.teddyguy.meetupapp.service.MeetUpEventService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MeetUpEventServiceImpl implements MeetUpEventService {
    MeetUpEventRepository meetUpEventRepository;
    MeetUpUserRepository meetUpUserRepository;
    @Override
    public MeetUpEventSaveResponse save(String organizerUsername, MeetUpEventSaveRequest request) {
        MeetUpUser organizer = meetUpUserRepository.findByEmail(organizerUsername)
                .orElseThrow(EntityNotFoundException::new);

        MeetUpEvent event;

        if(request.id() == null){
            event = new MeetUpEvent();
        } else {
            event = meetUpEventRepository.findById(request.id()).orElse(new MeetUpEvent());
        }

        event.setName(request.name());
        event.setDate(request.date());
        event.setOrganizer(organizer);

        event = meetUpEventRepository.save(event);

        organizer.getOrganizedEvents().add(event);
        meetUpUserRepository.save(organizer);

        return MeetUpEventSaveResponse.from(event);
    }
    @Override
    public void join(Long meetUpEventId, String username) {
        MeetUpEvent event = meetUpEventRepository.findByIdWithConfirmedAttendees(meetUpEventId)
                .orElseThrow(EntityNotFoundException::new);

        if(event.getConfirmedAttendees().stream().anyMatch((attendee) -> attendee.getEmail().equals(username))){
            throw new IllegalStateException("user with email " + username + " already joinded the event");
        }

        MeetUpUser meetUpUser = meetUpUserRepository.findByEmail(username)
                .orElseThrow(EntityNotFoundException::new);

        event.getConfirmedAttendees().add(meetUpUser);
        event = meetUpEventRepository.save(event);
        meetUpUser.getJoinedEvents().add(event);
        meetUpUserRepository.save(meetUpUser);
    }

    @Override
    public List<MeetUpEventFetchResponse> search(String queryString) {
        if(queryString == null || queryString.trim().isBlank()){
            return meetUpEventRepository.findAllWithConfirmedAttendees().stream().map(MeetUpEventFetchResponse::from).toList();
        }
        System.out.println("ISS HERE, QUERY IS: " + queryString);
        return meetUpEventRepository.findByNameContainsIgnoreCaseWithConfirmedAttendees(queryString.trim())
                .stream().map(MeetUpEventFetchResponse::from).toList();
    }
}
