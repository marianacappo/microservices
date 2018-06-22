package com.microservice.secure.imagecatalogservice.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.secure.imagecatalogservice.domain.Image;
import com.microservice.secure.imagecatalogservice.service.ImageService;

@RestController
public class ImagesController {

	protected Logger logger = LoggerFactory.getLogger(ImagesController.class.getName());
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping("/allimages")
	public Collection<Image> allImages() {
		logger.info("Image-catalog-service - allImages()");
		return imageService.findAllImages();
	}
	
	@RequestMapping("/bestimages")
    public Collection<Image> readImages() {
    	logger.info("Image-catalog-service - readImages()");
    	return imageService.readImages();
    }
	
	
}
