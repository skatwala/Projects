package com.disney.studios.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.disney.studios.errors.DogDoesNotExistException;
import org.springframework.http.HttpStatus;

import com.disney.studios.domain.Dog;
import com.disney.studios.service.DogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
 
 @Api(value="dogs", description = "Data service operations on dogs",
 tags=("dogs"))
public class DogController {
	@Autowired
	private DogService dogService;

	@RequestMapping(value = "/images/breed",method = RequestMethod.GET)
	@ApiOperation(value="Get All Dog Images for a specific Breed.", notes="Find all dog images for a specific Breed.", nickname="findAllImagesForBreed")
	public List<Dog> findAllImagesForBreed(@RequestParam(name = "breed", required = true) String breed   ) {
		return dogService.getBreedDetails(breed);
 
	}
	
	@RequestMapping(value = "/allimagesbybreed",method = RequestMethod.GET)
	@ApiOperation(value="Get All Dog Images grouped by Breed.", notes="Find all dog images grouped by Breed.", nickname="findAllImagesGroupedByBreed")
	public List<Dog> getAllImagesByBreed(){
		return dogService.getAllImagesByBreed();
 
	}
	 
	@RequestMapping(value = "/images/{imageId}", method = RequestMethod.GET)
	 @ApiOperation(value="Get details of a single dog (or image)", notes="Gets all details in the of a given dog", nickname="getDogs")
	public Dog findImageDetails(@PathVariable(value = "imageId") Integer imageId) {
		Dog dog = dogService.getDogDetails(imageId);

		return dog;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DogDoesNotExistException.class)
	public String return400(DogDoesNotExistException ex) {
		return ex.getMessage();

	}

}
