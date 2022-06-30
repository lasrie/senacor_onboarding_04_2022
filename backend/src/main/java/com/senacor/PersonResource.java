package com.senacor;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
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


import com.senacor.dto.PersonDTO;
import com.senacor.orm.Person;
import com.senacor.orm.PersonRepository;

@Path("/people")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonRepository personRepository;

    @POST
    @Transactional
    public Response createPerson (Person person){
        Person createdPerson = personRepository.createPerson(person);
        return Response.created(URI.create("/" + createdPerson.getId())).build();
    }

    @GET
    @Path("/")
    public List<Person> list() {
        return personRepository.listAll();
    }


    @GET
    @Path("/{id}")
    public Person getOnePerson(Long id) {
        return personRepository.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(Long id){
        Person entity = personRepository.findById(id);
        if(entity == null){
            throw new NotFoundException();
        }
        personRepository.delete(entity);
        return Response.accepted().build();

    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Person update(Long id, Person person) {
        return personRepository.updatePerson(person, id);
    }


    
    
}
