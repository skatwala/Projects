package com.disney.studios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.studios.domain.Breed;
import com.disney.studios.domain.Dog;
import com.disney.studios.errors.DogDoesNotExistException;
import com.disney.studios.repository.BreedRepository;
import com.disney.studios.repository.DogRepository;

@Service
public class DogService {
	private DogRepository dogRepository;

	private BreedRepository breedRepository;

	@Autowired
	public DogService(DogRepository dogRepository, BreedRepository breedRepository) {
		this.dogRepository = dogRepository;
		this.breedRepository = breedRepository;
	}

	public Dog createDog(String name, String breedName, String imageURL) {
		Breed breed = breedRepository.findByName(breedName);
		if (breed == null) {
			throw new RuntimeException("Dog Breed does not exist: " + breedName);
		}
		return dogRepository.save(new Dog(name, breed, imageURL));
	}

	public Dog getDogDetails(Integer id) {
		Dog dog = dogRepository.findOne(id);
		if (dog == null) {
			throw new DogDoesNotExistException("Dog  does not exist: " + id);
		}
		return dog;
	}

	public List<Dog> getBreedDetails(String breedName) {
		Breed breed = breedRepository.findByName(breedName);
		if (breed == null) {
			throw new RuntimeException("Dog Breed does not exist: " + breedName);
		}
		return dogRepository.findByBreedCode(breed.getCode());
	}
	
	public List<Dog> getAllImagesByBreed() {
		 
		return dogRepository.getAllDogImagesByBreed();
	}

	/**
	 * Calculate the number of Dogs in the Database.
	 *
	 * @return the total.
	 */
	public long total() {
		return dogRepository.count();
	}

}
