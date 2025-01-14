package com.senacor.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class PersonDTO {
    public Long id;
    public String firstName;
    public String lastName;
    public Integer age;
    
    public PersonDTO(Long id, String firstName, String lastName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    
}
