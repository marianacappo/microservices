package com.microservices.greeting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.greeting.model.Greeting;

@RestController
public class GreetingController {
	
	protected Logger logger = LoggerFactory.getLogger(GreetingController.class);
	
	private static final String greetingTemplate = "Hola, %s!";
	
	@RequestMapping("/greeting/{name}")
	public Greeting greeting(@PathVariable("name") String name) {
		logger.info("microservice-greeting...........");
		Greeting greet = new Greeting(String.format(greetingTemplate, name));
		logger.info("microservice-greeting: "+greet.getContent());
		return greet;
	}
}
