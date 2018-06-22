package com.microservices.clientgreeting.dtos;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonRootName("name")
public class ImageDto {
	
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonValue
	public String getName() {
		return this.name;
	}
	
}
