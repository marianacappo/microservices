package com.microservices.clientgreeting.dtos;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("greeting")
public class GreetingDto {

	protected String content;
	
	// Default
	protected GreetingDto() {
		this.content = "Hola!";
	}
	
	public GreetingDto(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
}
