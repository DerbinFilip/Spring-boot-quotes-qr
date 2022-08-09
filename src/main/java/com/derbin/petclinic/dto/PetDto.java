package com.derbin.petclinic.dto;

public class PetDto {
    private Long id;
    private String name;
    private String breed;

    private Long age;

    public PetDto setId(Long id) {
        this.id = id;
        return this;
    }

    public PetDto setName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public Long getAge() {
        return age;
    }

    public PetDto setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public PetDto setAge(Long age) {
        this.age = age;
        return this;
    }
}
