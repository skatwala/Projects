package com.disney.studios.web;

import java.util.AbstractMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.disney.studios.domain.Dog;
import com.disney.studios.domain.DogRating;
import com.disney.studios.errors.UserAlreadyVotedException;
import com.disney.studios.repository.DogRatingRepository;
import com.disney.studios.repository.DogRepository;
import com.disney.studios.service.DogRatingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/dogs/{dogId}/ratings")
@Api(value = "Ratings on Dogs", description = "Ratings on Dogs", tags = ("Ratings on Dogs"))
public class DogRatingController {

	DogRatingService dogRatingService;
	DogRatingRepository dogRatingRepository;
	DogRepository dogRepository;

	public DogRatingController(DogRatingService dogRatingService, DogRatingRepository dogRatingRepository,
			DogRepository dogRepository) {
		this.dogRatingService = dogRatingService;
		this.dogRatingRepository = dogRatingRepository;
		this.dogRepository = dogRepository;

	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create Dog Rating, each client can only vote once", notes = "Create Dog Rating, each client can only vote once", nickname = "Create Dog Rating, each client can only vote once")
	public void createDog(@PathVariable(value = "dogId") int dogId, @RequestBody @Validated RatingsDTO ratingsDTO) {
		dogRatingService.createDogRating(dogId, ratingsDTO);
	}

	@ApiOperation(value = "Get All Ratings for a given dog", notes = "Gets all ratings for given dog in the system", nickname = "get Ratings for a given dog")
	@RequestMapping(method = RequestMethod.GET)
	public Page<RatingsDTO> getAllRatingsForDog(@PathVariable(value = "dogId") int dogId, Pageable pageable) {
		Dog dog = verifyDog(dogId);
		Page<DogRating> dogRatingPage = dogRatingRepository.findByPkDogId(dogId, pageable);
		List<RatingsDTO> ratingDtoList = dogRatingPage.getContent().stream().map(dogRating -> toDto(dogRating))
				.collect(Collectors.toList());
		return new PageImpl<RatingsDTO>(ratingDtoList, pageable, dogRatingPage.getTotalPages());
	}

	@ApiOperation(value = "Update  Ratings for a given dog via put", notes = "Update ratings for given dog in the system", nickname = "update  Ratings for a given dog")

	@RequestMapping(method = RequestMethod.PUT)
	public RatingsDTO updateWithPut(@PathVariable(value = "dogId") int dogId,
			@RequestBody @Validated RatingsDTO ratingDto) {
		DogRating rating = verifyDogRating(dogId, ratingDto.getCustomerId());
		rating.setScore(ratingDto.getScore());
		rating.setComment(ratingDto.getComment());
		return toDto(dogRatingRepository.save(rating));
	}

	@ApiOperation(value = "Get Average Score Rating for the Dog", notes = "Get Average Score Rating for the Dog", nickname = "Get Average Score Rating for the Dog")

	@RequestMapping(method = RequestMethod.GET, path = "/average")
	public AbstractMap.SimpleEntry<String, Double> getAverage(@PathVariable(value = "dogId") int dogId) {

		List<DogRating> ratings = dogRatingRepository.findByPkDogId(dogId);
		OptionalDouble average = ratings.stream().mapToInt(DogRating::getScore).average();
		double result = average.isPresent() ? average.getAsDouble() : null;
		return new AbstractMap.SimpleEntry<String, Double>("average", result);
	}

	@ApiOperation(value = "Get Count of Likes for each dog", notes = "Get Count of Likes for each dog", nickname = "Get Count of Likes for each dog")

	@RequestMapping(method = RequestMethod.GET, path = "/likecount")
	public AbstractMap.SimpleEntry<String, Double> getLikeCount(@PathVariable(value = "dogId") int dogId) {
		 
		List<DogRating> ratings = dogRatingRepository.findByPkDogId(dogId);

		return new AbstractMap.SimpleEntry<String, Double>("likes", (double) ratings.size());
	}

	private Dog verifyDog(Integer dogId) throws NoSuchElementException {
		Dog dog = dogRepository.findOne(dogId);
		if (dog == null) {
			throw new NoSuchElementException("Dog does not exist " + dogId);
		}
		return dog;
	}

	/**
	 * Exception handler if NoSuchElementException is thrown in this Controller
	 *
	 * @param ex
	 * @return Error message String.
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public String return400(NoSuchElementException ex) {
		return ex.getMessage();

	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(UserAlreadyVotedException.class)
	public String return400(UserAlreadyVotedException ex) {
		return ex.getMessage();

	}

	private RatingsDTO toDto(DogRating dogRating) {
		return new RatingsDTO(dogRating.getScore(), dogRating.getComment(), dogRating.getPk().getCustomerId());
	}

	private DogRating verifyDogRating(int dogId, int customerId) throws NoSuchElementException {
		DogRating rating = dogRatingRepository.findByPkDogIdAndPkCustomerId(dogId, customerId);
		if (rating == null) {
			throw new NoSuchElementException("Dog-Rating pair for request(" + dogId + " for customer" + customerId);
		}
		return rating;
	}

}
