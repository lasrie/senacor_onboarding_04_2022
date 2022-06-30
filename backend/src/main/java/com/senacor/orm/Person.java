package com.senacor.orm;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;




@Entity
public class Person{
    @Id @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private Integer age;

    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "person_groups", 
        joinColumns = { @JoinColumn(name = "person_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "groups_id") }
    )
    public Set<Group> memberships = new HashSet<>();


    public Long getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Integer getAge() {
        return age;
    }


    public void setAge(Integer age) {
        this.age = age;
    }


    public Set<Group> getMemberships() {
        return memberships;
    }


    public void setMemberships(Set<Group> memberships) {
        this.memberships = memberships;
    }

    


}