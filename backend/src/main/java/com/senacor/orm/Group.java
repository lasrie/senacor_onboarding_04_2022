package com.senacor.orm;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Group {
    @Id
    @SequenceGenerator(name = "groupSeq", sequenceName = "group_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "groupSeq")
    private Integer id;

    private Timestamp creationDate;

    private Timestamp meetingDate;

    private ArrayList<Person> members;

    public ArrayList<Person> getMembers() {
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