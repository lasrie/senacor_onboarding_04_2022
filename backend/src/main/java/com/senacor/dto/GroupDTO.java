package com.senacor.dto;

import java.time.OffsetDateTime;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class GroupDTO {

    public Long id;


    public OffsetDateTime creationDate;

    public OffsetDateTime dateMeeting;

    public GroupDTO(Long id, OffsetDateTime creationDate, OffsetDateTime dateMeeting) {
        this.id = id;
        this.creationDate = creationDate;
        this.dateMeeting = dateMeeting;
    }
    
}