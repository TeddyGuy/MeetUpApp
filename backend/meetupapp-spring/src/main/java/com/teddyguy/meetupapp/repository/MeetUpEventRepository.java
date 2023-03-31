package com.teddyguy.meetupapp.repository;

import com.teddyguy.meetupapp.model.MeetUpEvent;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetUpEventRepository extends JpaRepository<MeetUpEvent, Long> {

    @EntityGraph(value = "MeetUpEvent.confirmedAttendees")
    @Query("select m from MeetUpEvent m where upper(m.name) like upper(concat('%', ?1, '%'))")
    List<MeetUpEvent> findByNameContainsIgnoreCaseWithConfirmedAttendees(String name);

    @EntityGraph(value = "MeetUpEvent.confirmedAttendees")
    @Query("SELECT e FROM MeetUpEvent e WHERE e.id = ?1")
    Optional<MeetUpEvent> findByIdWithConfirmedAttendees(Long id);

    @EntityGraph(value = "MeetUpEvent.confirmedAttendees")
    @Query("select m from MeetUpEvent m")
    List<MeetUpEvent> findAllWithConfirmedAttendees();





}
