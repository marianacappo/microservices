package com.microservices.clientgreeting.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.clientgreeting.dtos.GreetingDto;
import com.microservices.clientgreeting.dtos.ImageDto;
import com.microservices.clientgreeting.service.ClientGreetingService;

@RestController
public class ClientGreetingController {
	
	@Autowired
	protected ClientGreetingService clientGreetingService;
	protected Logger logger = LoggerFactory.getLogger(ClientGreetingController.class);
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/greeting")
	public String goHome() {
		return "index";
	}
	
	@RequestMapping("/greeting/{name}")
	public String greeting(Model model, @PathVariable("name") String name) {
	
		logger.info("microservice-clientGreeting greeting() invoked: " + name);

		GreetingDto greeting = clientGreetingService.greeting(name);
		
		logger.info("microservice-clientGreeting greeting() found: " + greeting.getContent());
	
		model.addAttribute("greeting", greeting.getContent());
	
		return greeting.getContent();		
	}
	
	@RequestMapping("/user/images")
	public Collection<ImageDto> images() {
		logger.info("microservice-clientuser images(): ");
		Collection<ImageDto> images = clientGreetingService.images();
		return images;
	}
	
	@RequestMapping("/user/bestimages")
	public Collection<ImageDto> bestImages() {
		logger.info("microservice-clientuser bestImages(): ");
		Collection<ImageDto> images = clientGreetingService.bestImages();
		return images;
	}
}
