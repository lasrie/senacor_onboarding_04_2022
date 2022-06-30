package com.senacor.orm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
        meetGroup.setDateCreated(OffsetDateTime.now());
        meetGroup.setDateMeeting(findValidMeetingTime());
        persist(meetGroup);

        for(int i = 0; i< groupSize; i++){
            Person randomPerson = all.get(i);
            random.add(randomPerson);
            randomPerson.memberships.add(meetGroup);
            returnList.add(new PersonDTO(randomPerson.getId(), randomPerson.getFirstName(), randomPerson.getLastName(), randomPerson.getAge()));
        }


       return returnList;
    }

    public void setRandomMeetingTime(Long id){
        Group group = this.findById(id);
        group.setDateMeeting(findValidMeetingTime());
        persist(group);
    }

    public OffsetDateTime findValidMeetingTime(){

        OffsetDateTime earliestStartTime = 
            OffsetDateTime.now();


        Random rand  = new Random();
        long offset = rand.nextLong((2*7*24));
        OffsetDateTime meetingWithOffset = earliestStartTime.plusHours(offset).withMinute(0).withSecond(0).withNano(0);

        if(isValidMeetingTime(meetingWithOffset)){
            return meetingWithOffset;
        }else{
            return findValidMeetingTime();
        }
    }

    /**
     * Placeholder: This would probably be replaced with an algorithm for checking the calendar or with more precise settings for time
     */
    public boolean isValidMeetingTime(OffsetDateTime date){
        if(date.getHour() < 8 || date.getHour() > 17){
            return false;
        }else{
            return true;
        }
    }

}