package com.senacor.orm;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;

@Entity
@Table(name="groups")
public class Group extends PanacheEntity{
    
    public String name;

    public OffsetDateTime dateCreated;

    public OffsetDateTime dateMeeting;

    @ManyToMany(
        mappedBy = "memberships"
    )
    public Set<Person> members = new HashSet<Person>();

    @Override
    public String toString() {
        return "Group [name=" + name + "]";
    }

}
