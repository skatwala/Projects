package com.disney.studios.service;
 

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.studios.domain.Dog;
import com.disney.studios.domain.DogRating;
import com.disney.studios.domain.DogRatingPK;
import com.disney.studios.errors.UserAlreadyVotedException;
import com.disney.studios.repository.DogRatingRepository;
import com.disney.studios.repository.DogRepository;
import com.disney.studios.web.RatingsDTO;
@Service
public class DogRatingService {

	private DogRatingRepository dogRatingRepository;
	private DogRepository dogRepository;
	
	@Autowired
	public DogRatingService(DogRatingRepository dogRatingRepository, DogRepository dogRepository) {
		this.dogRatingRepository=dogRatingRepository;
		this.dogRepository= dogRepository;
	}
	
	public void createDogRating(int dogId,RatingsDTO ratingsDTO ) {
		Dog dog = verifyDog(dogId);
		System.out.println(verifyIfUserVoted( dog,  ratingsDTO.getCustomerId()));
		//if (verifyIfUserVoted( dogId,  ratingsDTO.getCustomerId())){
		dogRatingRepository.save(new DogRating(new DogRatingPK(dog, ratingsDTO.getCustomerId()),
				ratingsDTO.getScore(), ratingsDTO.getComment()));
		 
		/*else {
			 
				throw new UserAlreadyVotedException("User " +ratingsDTO.getCustomerId()+" already voted for dog "+ dogId);
		}*/	
			 
	}
	
	public int getTotalVotes(int dogId) {
		List allRatings=(ArrayList)dogRatingRepository.findByPkDogId(dogId);
		return allRatings.size();
	}
	
	private boolean verifyIfUserVoted(Dog dog, Integer customerId) {
		DogRating dogRating = dogRatingRepository.findByPkDogAndPkCustomerId(dog, customerId);
		if (dogRating !=null) {
			throw new UserAlreadyVotedException("User " +customerId+" already voted for dog "+ dog);
			
		}
		return true;
	}
	
	private Dog verifyDog(Integer dogId) throws NoSuchElementException {
		Dog dog = dogRepository.findOne(dogId);
		if (dog == null) {
			throw new NoSuchElementException("Dog does not exist " + dogId);
		}
		return dog;
	}
	
}
