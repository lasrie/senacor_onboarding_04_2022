package com.senacor.orm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Person{

    @Id
    @SequenceGenerator(name = "personSeq", sequenceName = "person_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "personSeq")
    private Integer id;

    private String firstName;

    private String lastName;

    private int age;

    public int getAge(){
        return this.age;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName= lastName;
    }
}