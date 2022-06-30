package com.senacor.orm;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

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
