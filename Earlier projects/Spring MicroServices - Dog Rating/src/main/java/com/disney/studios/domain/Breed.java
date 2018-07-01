package com.disney.studios.domain;

 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Breed  {
	@Id
	private String code;
	@Column
	private String name;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	protected Breed(){
		
	}
	public Breed(String code, String name) {
		
		this.code = code;
		this.name = name;
	}
	
	

}
