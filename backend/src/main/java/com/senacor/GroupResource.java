package com.senacor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
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
    @Path("/group")
    @Transactional
    public List<PersonDTO> createGroup(){
        Long personsInDB = personRepository.count();
        if(personsInDB < groupSize){
            throw new NotEnoughPersonException(personsInDB);
        }
        
        List<Person> all = personRepository.listAll();
        Collections.shuffle(all);
        List<Person> random = new ArrayList<Person>();
        List<PersonDTO> returnList = new ArrayList<PersonDTO>();

        Group meetGroup = new Group();
        groupRepository.persist(meetGroup);

        for(int i = 0; i< groupSize; i++){
            Person randomPerson = all.get(i);
            random.add(randomPerson);
            randomPerson.memberships.add(meetGroup);
            returnList.add(new PersonDTO(randomPerson.getFirstName(), randomPerson.getLastName(), randomPerson.getAge()));
        }

       return returnList;
    }
    
}
