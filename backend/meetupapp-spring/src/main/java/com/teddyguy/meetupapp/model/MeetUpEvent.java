package com.teddyguy.meetupapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@NamedEntityGraph(name = "MeetUpEvent.confirmedAttendees", attributeNodes = @NamedAttributeNode("confirmedAttendees"))
public class MeetUpEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date date;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<MeetUpUser> confirmedAttendees;
    @ManyToOne(cascade = CascadeType.MERGE)
    private MeetUpUser organizer;

}
