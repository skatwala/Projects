package com.disney.studios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.disney.studios.domain.Dog;
@Repository
public interface DogRepository extends CrudRepository<Dog, Integer> {
		
		List<Dog> findByBreedCode(String code);
		
		@Query(value = "select count(c.imageURL), c.breed.code  from Dog c group by c.breed.code")
		List<Dog> getAllDogImagesByBreed();
	 
	}

 
