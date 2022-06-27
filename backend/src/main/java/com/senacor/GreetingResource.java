package com.senacor;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import com.senacor.dto.GroupDTO;
import com.senacor.dto.PersonDTO;
import com.senacor.orm.Group;
import com.senacor.orm.Person;


class NotEnoughPersonException extends RuntimeException {
    public final Long count;

    public NotEnoughPersonException(Long count) {
        this.count = count;
    }
}

@Path("/hello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @ServerExceptionMapper
    public RestResponse<String> mapException(NotEnoughPersonException x) {
        return RestResponse.status(Response.Status.NOT_FOUND, "Not enough persons in database to form a group. Current Count in db " + x.count);
    }

    
    @Inject
    EntityManager em;

    @Inject
    PersonService personService;

    @ConfigProperty(name = "onboarding.groupsize") 
    Long groupSize;


    @POST
    @Transactional
    public Response createPerson (Person person){
        Person.persist(person);
        return Response.created(URI.create("/persons/" + person.id)).build();
    }

    @GET
    @Path("/persons")
    public List<PersonDTO> list() {
        return Person.findAll().project(PersonDTO.class).list();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id){
        Person entity = Person.findById(id);
        if(entity == null){
            throw new NotFoundException();
        }
        entity.delete();
    }

    
    @PUT
    @Path("/{id}")
    @Transactional
    public Person update(Long id, Person person) {
        Person entity = Person.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        entity.firstName = person.firstName;
        entity.lastName = person.lastName;
        entity.age = person.age;
        entity.memberships = person.memberships;

        return entity;
    }

    @POST
    @Path("/group")
    @Transactional
    public List<PersonDTO> createGroup(){
        Long personsInDB = Person.count();
        if(personsInDB < groupSize){
            throw new NotEnoughPersonException(personsInDB);
        }
        
        List<Person> all = Person.listAll();
        Collections.shuffle(all);
        List<Person> random = new ArrayList<Person>();
        List<PersonDTO> returnList = new ArrayList<PersonDTO>();

        Group meetGroup = new Group();
        Group.persist(meetGroup);

        for(int i = 0; i< groupSize; i++){
            Person randomPerson = all.get(i);
            random.add(randomPerson);
            randomPerson.memberships.add(meetGroup);
            returnList.add(new PersonDTO(randomPerson.firstName, randomPerson.lastName, randomPerson.age));
        }

       return returnList;
    }

  

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello world";
    }
}