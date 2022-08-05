package com.derbin.petclinic.repository;

import com.derbin.petclinic.model.Quotes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotesRepo extends JpaRepository<Quotes, Long> {
}
