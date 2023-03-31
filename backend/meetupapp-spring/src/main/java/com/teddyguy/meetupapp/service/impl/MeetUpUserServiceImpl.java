package com.teddyguy.meetupapp.service.impl;

import com.teddyguy.meetupapp.dto.response.MeetUpEventFetchResponse;
import com.teddyguy.meetupapp.dto.response.MeetUpUserFetchResponse;
import com.teddyguy.meetupapp.repository.MeetUpUserRepository;
import com.teddyguy.meetupapp.service.MeetUpUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class MeetUpUserServiceImpl implements MeetUpUserService {

    private MeetUpUserRepository meetUpUserRepository;
    @Override
    public List<MeetUpUserFetchResponse> search(String queryString) {
        if(queryString == null || queryString.trim().isBlank()){
            return new ArrayList<>();
        }

        return meetUpUserRepository.findByNameContainsIgnoreCase(queryString)
                .stream().map(MeetUpUserFetchResponse::from)
                .toList();
    }
}
