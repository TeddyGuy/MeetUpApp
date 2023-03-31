package com.teddyguy.meetupapp.service.impl;

import com.teddyguy.meetupapp.dto.request.MeetUpEventSaveRequest;
import com.teddyguy.meetupapp.dto.response.MeetUpEventSaveResponse;
import com.teddyguy.meetupapp.model.MeetUpEvent;
import com.teddyguy.meetupapp.model.MeetUpUser;
import com.teddyguy.meetupapp.repository.MeetUpEventRepository;
import com.teddyguy.meetupapp.repository.MeetUpUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class MeetUpEventServiceImplTest {
    @Mock
    MeetUpUserRepository meetUpUserRepository;
    @Mock
    MeetUpEventRepository meetUpEventRepository;
    @InjectMocks
    MeetUpEventServiceImpl meetUpEventService;
    @Test
    void saveEventHappyDay() {
        //Arrange

        MeetUpUser organizer = Mockito.mock(MeetUpUser.class);
        Mockito.when(meetUpUserRepository.findByEmail(anyString())).thenReturn(Optional.of(organizer));
        Mockito.when(meetUpEventRepository.save(any(MeetUpEvent.class))).thenReturn(
                MeetUpEvent.builder().name("testEvent").date(new Date()).organizer(organizer).build()
        );

        //Act

        MeetUpEventSaveResponse response = meetUpEventService.save(
                "testMeetUpUser",
                new MeetUpEventSaveRequest(1L, "testEvent", new Date())
        );

        //Assert

        Assertions.assertThat(response).isNotNull();
    }
    @Test
    void saveEventOrganizerNotFound() {

        //Act & Assert

        Assertions.assertThatThrownBy(
                () -> meetUpEventService.save("test", new MeetUpEventSaveRequest(null, "test", new Date()))
        ).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void joinEventHappyDay() {

        //Arrange

        //Act

        meetUpEventService.join(1L, "obama@email.com");

        //Assert
    }
}
