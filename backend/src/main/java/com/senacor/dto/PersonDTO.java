package com.senacor.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class PersonDTO {
    public String firstName;
    public String lastName;
    public Integer age;
    
    public PersonDTO(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    
}
