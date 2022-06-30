package com.senacor.orm;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {
    
    public Person createPerson(Person person){
        persist(person);
        return person;
    }

    public Person updatePerson(Person person, Long id){
        Person entity = findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAge(person.getAge());
        entity.setMemberships(person.getMemberships());

        return entity;
    }
}
