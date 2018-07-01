package com.disney.studios.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Dog {

	@Id
	@GeneratedValue
	private Integer id;
	@Column
	private String name;
	 
	
    @ManyToOne
    private Breed breed;

	
	@Column
	private String imageURL;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Integer getId() {
		return id;
	}

	protected Dog() {

	}

	public Dog(String name, Breed breed, String imageURL) {
		super();
		this.name = name;
		this.breed = breed;
		this.imageURL = imageURL;
	}

}
