package com.derbin.petclinic.controller;

import com.derbin.petclinic.dto.PetDto;
import com.derbin.petclinic.mapper.PetMapper;
import com.derbin.petclinic.model.Pets;
import com.derbin.petclinic.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pets")
public class PetController {
    private final PetService petService;
    private final PetMapper petMapper;

    public PetController(PetService petService, PetMapper petMapper) {
        this.petService = petService;
        this.petMapper = petMapper;
    }

    @GetMapping
    public ResponseEntity<List<PetDto>> getAllPets() {
        final List<Pets> pets = petService.getAllPets();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(petMapper.toDto(pets));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDto> getPetById(@PathVariable Long id) {
        final Pets pets = petService.getPetById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(petMapper.toDto(pets));
    }

    @PostMapping
    public ResponseEntity<PetDto> createPet(@RequestBody @Valid final PetDto petsDto) {
        final Pets pets = petMapper.toEntity(petsDto);
        final Pets createdPet = petService.createPet(pets);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(petMapper.toDto(createdPet));
    }

    @PutMapping
    public ResponseEntity<?> updatePet(@RequestBody @Valid final PetDto petDto) {
        final Pets pets = petMapper.toEntity(petDto);
        final Pets createdPet = petService.updatePet(pets);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(petMapper.toDto(createdPet));
    }

    @DeleteMapping("/{id}")
    public void deletePet(@RequestBody final Long id) {
        petService.deletePet(id);
    }
}
