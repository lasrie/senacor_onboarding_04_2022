package com.senacor;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.senacor.orm.Person;

@Path("/hello")
public class GreetingResource {
    @Inject
    EntityManager em;

    @Transactional
    public void createPerson (String firstName, String lastName){
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        em.persist(person);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        createPerson("Hello", "World");
        return "hello world";
    }
}