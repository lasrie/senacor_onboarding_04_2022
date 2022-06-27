package com.senacor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.senacor.orm.Person;

@ApplicationScoped
public class PersonService {
    @Inject
    EntityManager em;


    public String greeting(String name) {
        return "hello " + name;
    }




    
}
