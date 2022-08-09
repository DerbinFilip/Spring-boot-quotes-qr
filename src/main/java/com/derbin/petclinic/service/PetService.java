package com.derbin.petclinic.service;

import com.derbin.petclinic.exception.ApplicationException;
import com.derbin.petclinic.exception.PetNotFoundException;
import com.derbin.petclinic.exception.UpdatingEntityWithoutIdException;
import com.derbin.petclinic.model.Pets;
import com.derbin.petclinic.repository.PetsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetsRepo petsRepo;

    public PetService(PetsRepo petsRepo) {
        this.petsRepo = petsRepo;
    }

    public Pets createPet(final Pets pets) {
        if (getByName(pets))
            throw new ApplicationException("Pet already exists with this name!");
        return petsRepo.save(pets);
    }

    private boolean getByName(Pets pets) {
        return petsRepo.findByName(pets.getName()) != null;
    }

    public Pets updatePet(final Pets pets) {
        if (pets.getId() == null) {
            throw new UpdatingEntityWithoutIdException(Pets.class);
        }
        return petsRepo.save(pets);
    }

    public Pets getPetById(final Long id) {
        return petsRepo.findById(id).orElseThrow(
                () -> new PetNotFoundException("Couldn't find pet with id: " + id));
    }

    public List<Pets> getAllPets() {
        return petsRepo.findAll();
    }

    public void deletePet(final Long id) {
        petsRepo.deleteById(id);
    }
}
