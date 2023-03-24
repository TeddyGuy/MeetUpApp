package com.teddyguy.meetupapp.service.impl;

import com.teddyguy.meetupapp.dto.response.MeetUpUserFetchResponse;
import com.teddyguy.meetupapp.repository.MeetUpUserRepository;
import com.teddyguy.meetupapp.service.MeetUpUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class MeetUpServiceImpl implements MeetUpUserService {

    private MeetUpUserRepository meetUpUserRepository;
    @Override
    public List<MeetUpUserFetchResponse> search(String queryString) {
        return meetUpUserRepository.findByNameContainsIgnoreCase(queryString)
                .stream().map(MeetUpUserFetchResponse::from)
                .toList();
    }
}
