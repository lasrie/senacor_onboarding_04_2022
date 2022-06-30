package com.senacor;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;



import java.util.HashMap;
import java.util.Map;

@QuarkusTest
@TestHTTPEndpoint(GroupResource.class) 
public class GroupResourceTest {

     
    @Test
    public void testListEndpoint(){
        when().get()
        .then()
        .statusCode(200);
    }
    

    @Test
    public void testGroupCreationEndpoint(){

        RequestSpecification personEndpoint = new RequestSpecBuilder().setBasePath("/people").build();

        Map<String, Object>  person1 = new HashMap<>();
        person1.put("firstName", "John");
        person1.put("lastName", "Doe");
        person1.put("age", 3);
    
        String location = 
        given().
            spec(personEndpoint).
            contentType("application/json").
            body(person1).
        when().
            post("/").
        then().
            statusCode(201).
            extract().response().header("Location");

        int personID = Character.getNumericValue(location.charAt(location.length()-1));
        person1.put("id", personID);

        Map<String, Object>  person2 = new HashMap<>();
        person2.put("firstName", "Franziska");
        person2.put("lastName", "Häring");
        person2.put("age", 23);

        location = 
        given().
            spec(personEndpoint).
            contentType("application/json").
            body(person2).
        when().
            post("/").
        then().
            statusCode(201).
            extract().response().header("Location");

        personID = Character.getNumericValue(location.charAt(location.length()-1));
        person2.put("id", personID);

        Map<String, Object>  person3 = new HashMap<>();
        person3.put("firstName", "Schorsch");
        person3.put("lastName", "Hoß");
        person3.put("age", 223);

        location = 
        given().
            spec(personEndpoint).
            contentType("application/json").
            body(person3).
        when().
            post("/").
        then().
            statusCode(201).
            extract().response().header("Location");

        personID = Character.getNumericValue(location.charAt(location.length()-1));
        person3.put("id", personID);

        given().
            contentType("application/json").
        when().
            post("/").
        then().
            statusCode(200);
        
    }
}
