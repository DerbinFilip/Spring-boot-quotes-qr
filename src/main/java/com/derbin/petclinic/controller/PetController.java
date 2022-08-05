package com.derbin.petclinic.controller;

import com.derbin.petclinic.model.Pets;
import com.derbin.petclinic.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping()
    public List<Pets> getAllPets() {
        final List<Pets> pets = petService.getAllPets();
        return pets;
    }

    @GetMapping("/{id}")
    public Pets getPetById(@PathVariable Long id) {
        final Pets pets = petService.getPetById(id);
        return pets;
    }

    @PostMapping
    public Pets createPet(@RequestBody final Pets pets) {
        final Pets pet = petService.createPet(pets);
        return pet;
    }

    @PutMapping
    public Pets updatePet(@RequestBody final Pets pet) {
        return petService.updatePet(pet);
    }

    @DeleteMapping("/{id}")
    public void deletePet(@RequestBody final Long id) {
        petService.deletePet(id);
    }
}
