package com.microservice.secure.imagecatalogservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Image {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	public Image() {}
	
	public Image(String name) {
		this.name = name;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}