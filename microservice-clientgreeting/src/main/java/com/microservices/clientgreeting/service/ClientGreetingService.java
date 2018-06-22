package com.microservices.clientgreeting.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.clientgreeting.dtos.GreetingDto;
import com.microservices.clientgreeting.dtos.ImageDto;

@Service
public class ClientGreetingService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	
	public static final String SERVICE_URL = "http://GREETING-SERVICE";
	
	public static final String SERVICE_IMG_URL = "http://IMAGE-CATALOG-SERVICE";
	
	protected Logger logger = LoggerFactory.getLogger(ClientGreetingService.class);

	public GreetingDto greeting (String name) {
		logger.info("microservice-clientGreeting - calling microservice-greeting...");
		GreetingDto greeting =  restTemplate.getForObject(SERVICE_URL + "/greeting/{name}",GreetingDto.class, name);
		return greeting;
	}
	
	public Collection<ImageDto> images() {
		logger.info("microservice-clientuser - calling microservice-image-catalog-service...");
		Collection<ImageDto> images = restTemplate.getForObject(SERVICE_IMG_URL + "/allimages", Collection.class);
		return images;
	}
	
	public Collection<ImageDto> bestImages() {
		logger.info("microservice-clientuser - calling microservice-image-catalog-service...");
		Collection<ImageDto> images = restTemplate.getForObject(SERVICE_IMG_URL + "/bestimages", Collection.class);
		return images;
	}
}
