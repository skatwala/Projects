package com.disney.studios.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.disney.studios.domain.Breed;

@Repository
public interface BreedRepository extends CrudRepository<Breed, String> {

    /**
     * Lookup a tour package by the name.
     *
     * @param name name of the tour.
     * @return TourPackage if found, null otherwise.
     */
    Breed findByName( String name);
}

 
