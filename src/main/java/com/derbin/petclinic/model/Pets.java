package com.derbin.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String breed;

    private Long age;

    public Long getId() {
        return id;
    }

    public Pets setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pets setName(String name) {
        this.name = name;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public Pets setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public Long getAge() {
        return age;
    }

    public Pets setAge(Long age) {
        this.age = age;
        return this;
    }
}
