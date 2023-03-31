package com.teddyguy.meetupapp.repository;

import com.teddyguy.meetupapp.model.MeetUpUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetUpUserRepository extends JpaRepository<MeetUpUser, Long> {
    Optional<MeetUpUser> findByEmail(String email);

    @Query("select m from MeetUpUser m where upper(m.name) like upper(concat('%', ?1, '%'))")
    List<MeetUpUser> findByNameContainsIgnoreCase(String name);


}
