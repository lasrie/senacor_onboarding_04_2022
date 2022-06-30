package com.senacor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.senacor.orm.Person;
import com.senacor.orm.PersonRepository;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class PersonRepositoryTest {

    @InjectMock
    PersonRepository personRepository;

    
    @Test
    public void testPersonCreation(){
        Assertions.assertEquals(0, personRepository.count());

        Person testPerson = new Person();
        testPerson.setFirstName("Testname");
        testPerson.setLastName("lastName");;
        testPerson.setAge(22);

    
        Mockito.when(personRepository.findById(12L)).thenReturn(testPerson);
        Assertions.assertSame(testPerson, personRepository.findById(12L));

    } 
}
