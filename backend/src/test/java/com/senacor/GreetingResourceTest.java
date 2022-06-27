package com.senacor;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.senacor.orm.Person;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.UUID;

@QuarkusTest
public class GreetingResourceTest {


    @Test
    public void Person() {
        PanacheMock.mock(Person.class);

        // Mocked classes always return a default value
        Assertions.assertEquals(0, Person.count());

        Mockito.when(Person.count()).thenReturn(23L);
        Assertions.assertEquals(23, Person.count());

    }

    @Test
    public void testPersonCreationEndpoint(){
       Person testPerson = new Person();
       testPerson.firstName ="Testname";
       testPerson.lastName = "LastName";
       testPerson.age = 22;

    }



}