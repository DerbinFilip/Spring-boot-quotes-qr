package com.derbin.petclinic.repository;

import com.derbin.petclinic.model.Pets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetsRepo extends JpaRepository<Pets, Long> {
    Pets findByName(String name);
}
