package com.teddyguy.meetupapp.repository;

import com.teddyguy.meetupapp.model.MeetUpUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetUpUserRepository extends JpaRepository<MeetUpUser, Long> {
    Optional<MeetUpUser> findByEmail(String email);
}
