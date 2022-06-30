package com.senacor.orm;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.senacor.dto.PersonDTO;
import com.senacor.exceptions.NotEnoughPersonException;

import io.quarkus.hibernate.orm.panache.PanacheRepository;



@ApplicationScoped
public class GroupRepository implements PanacheRepository<Group>{

    @Inject
    PersonRepository personRepository;

    @Transactional
    public List<PersonDTO> createGroup(Long groupSize){
        Long personsInDB = personRepository.count();
        if(personsInDB < groupSize){
            throw new NotEnoughPersonException(personsInDB);
        }
        
        List<Person> all = personRepository.listAll();
        Collections.shuffle(all);
        List<Person> random = new ArrayList<Person>();
        List<PersonDTO> returnList = new ArrayList<PersonDTO>();

        Group meetGroup = new Group();
        persist(meetGroup);

        for(int i = 0; i< groupSize; i++){
            Person randomPerson = all.get(i);
            random.add(randomPerson);
            randomPerson.memberships.add(meetGroup);
            returnList.add(new PersonDTO(randomPerson.getFirstName(), randomPerson.getLastName(), randomPerson.getAge()));
        }

       return returnList;
    }

    public void setMeetingTime(Long id, OffsetDateTime date){
        Group group = this.findById(id);
        group.setDateMeeting(date);
        persist(group);
    }

}