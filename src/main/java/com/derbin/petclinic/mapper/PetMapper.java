package com.derbin.petclinic.mapper;

import com.derbin.petclinic.dto.PetDto;
import com.derbin.petclinic.model.Pets;
import org.springframework.stereotype.Component;

@Component
public class PetMapper extends ApplicationMapper<Pets, PetDto> {

    @Override
    public Pets toEntity(PetDto petDto) {
        return new Pets()
                .setId(petDto.getId())
                .setName(petDto.getName())
                .setBreed(petDto.getBreed())
                .setAge(petDto.getAge());
    }

    @Override
    public PetDto toDto(Pets pets) {
        return new PetDto()
                .setId(pets.getId())
                .setName(pets.getName())
                .setBreed(pets.getBreed())
                .setAge(pets.getAge());
    }
}
