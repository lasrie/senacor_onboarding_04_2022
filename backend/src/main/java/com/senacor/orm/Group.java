package com.senacor.orm;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "group")
public class Group {
    @Id
    @SequenceGenerator(name = "groupSeq", sequenceName = "group_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "groupSeq")
    private Integer id;

    @Column(name ="date_created")
    private Timestamp creationDate;

    @Column(name ="date_meeting")
    private Timestamp meetingDate;

    @ManyToMany(mappedBy = "memberships", fetch = FetchType.EAGER)
    private Set<Person> members = new HashSet<>();

    public Set<Person> getMembers() {
        return members;
    }

    public void addMember(Person newMember) {
        this.members.add(newMember);
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Timestamp meetingDate) {
        this.meetingDate = meetingDate;
    }


}