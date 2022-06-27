package com.senacor.dto;

import java.time.OffsetDateTime;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class GroupDTO {

    public String name;

    public OffsetDateTime creationDate;

    public OffsetDateTime dateMeeting;

    public GroupDTO(String name, OffsetDateTime creationDate, OffsetDateTime dateMeeting) {
        this.name = name;
        this.creationDate = creationDate;
        this.dateMeeting = dateMeeting;
    }
    
}