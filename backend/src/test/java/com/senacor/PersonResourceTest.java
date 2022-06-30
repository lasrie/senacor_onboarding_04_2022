package com.senacor;

import static io.restassured.RestAssured.when;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import javax.json.JsonObject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(PersonResource.class) 
public class PersonResourceTest {
    
    @Test
    public void testListEndpoint(){
        when().get()
        .then()
        .statusCode(200);
    }

    @Test
    public void testPersonCreationEndpoint(){
        Map<String, Object>  jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "John");
        jsonAsMap.put("lastName", "Doe");
        jsonAsMap.put("age", 3);
    
        given().
            contentType("application/json").
            body(jsonAsMap).
        when().
            post().
        then().
            statusCode(201);

        when().get()
            .then()
            .statusCode(200)
            .body("firstName", hasItems("John"))
            .body("lastName", hasItems("Doe"));
    }

    @Test
    public void testPersonUpdateEndpoint(){
        Map<String, Object>  jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "John");
        jsonAsMap.put("lastName", "Doe");
        jsonAsMap.put("age", 3);
        String location =
        given().
            contentType("application/json").
            body(jsonAsMap).
        when().
            post().
        then().
            statusCode(201).
            extract().response().header("Location");

        int personID = Character.getNumericValue(location.charAt(location.length()-1));

        when().get()
        .then()
        .statusCode(200)
        .body("[0].firstName", equalTo("John"))
        .body("id", hasItem(personID));

        Map<String, Object>  updatePerson = new HashMap<>();
        updatePerson.put("firstName", "Lukas");
        updatePerson.put("lastName", "Updated");
        updatePerson.put("age", 3);
        updatePerson.put("id", personID);

        given().
            contentType("application/json").
            body(updatePerson).
        when().
            put(("/" + personID)).
        then().
            statusCode(200).and().
            body("firstName", equalTo("Lukas")).
            body("lastName", equalTo("Updated")).
            body("id", equalTo(personID));
    }

    @Test
    public void testPersonDeletionEndpoint(){
        Map<String, Object>  jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "Try");
        jsonAsMap.put("lastName", "Deletion");
        jsonAsMap.put("age", 3);
        String location =
        given().
            contentType("application/json").
            body(jsonAsMap).
        when().
            post().
        then().
            statusCode(201).
            extract().response().header("Location");

        int personID = Character.getNumericValue(location.charAt(location.length()-1));

        given().
        contentType("application/json").
        when().
            delete("/" + personID).
        then().
            statusCode(202);


    }


    
}
