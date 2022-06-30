package com.senacor.orm;

import java.time.OffsetDateTime;

import java.util.HashSet;

import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.Table;

@Entity
@Table(name="groups")
public class Group {
    @Id @GeneratedValue
    private Long id;

    private OffsetDateTime dateCreated;

    private OffsetDateTime dateMeeting;

    @ManyToMany(
        mappedBy = "memberships"
    )
    private Set<Person> members = new HashSet<Person>();

    
    public Long getId() {
        return id;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getDateMeeting() {
        return dateMeeting;
    }

    public void setDateMeeting(OffsetDateTime dateMeeting) {
        this.dateMeeting = dateMeeting;
    }


}
