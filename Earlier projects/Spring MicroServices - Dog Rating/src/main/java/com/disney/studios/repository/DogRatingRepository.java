package com.disney.studios.repository;


import java.util.List;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.disney.studios.domain.Dog;
import com.disney.studios.domain.DogRating;
import com.disney.studios.domain.DogRatingPK;

public interface DogRatingRepository extends CrudRepository<DogRating, DogRatingPK> {
	List<DogRating> findByPkDogId(Integer dogId);

     
    Page<DogRating> findByPkDogId(Integer dogId, Pageable pageable);

     
    DogRating findByPkDogAndPkCustomerId(Dog dog, Integer customerId);
    
    DogRating findByPkDogIdAndPkCustomerId(Integer dogId, Integer customerId);
 
}
