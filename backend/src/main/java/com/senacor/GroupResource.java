package com.senacor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.senacor.dto.PersonDTO;
import com.senacor.exceptions.NotEnoughPersonException;
import com.senacor.orm.Group;
import com.senacor.orm.GroupRepository;
import com.senacor.orm.Person;
import com.senacor.orm.PersonRepository;

@Path("/groups")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource {

    @ConfigProperty(name = "onboarding.groupsize") 
    Long groupSize;

    @Inject
    PersonRepository personRepository;

    @Inject
    GroupRepository groupRepository;

    @POST
    @Path("/")
    @Transactional
    public List<PersonDTO> createGroup(){
           return groupRepository.createGroup(groupSize);
    }

    @GET
    @Path("/")
    public List<Group> list() {
        return groupRepository.listAll();
    }
    
}
