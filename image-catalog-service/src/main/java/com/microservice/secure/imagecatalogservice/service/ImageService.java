package com.microservice.secure.imagecatalogservice.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.secure.imagecatalogservice.domain.Image;
import com.microservice.secure.imagecatalogservice.repositories.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;
	
    public Collection<Image> readImages() {
    	return imageRepository.findAll().stream()
    			.filter(this::isBestOne)
    			.collect(Collectors.toList());
    }
    
    private boolean isBestOne(Image image) {
        return !image.getName().equals("Batman") &&
                !image.getName().equals("Black Panther") &&
                !image.getName().equals("Flash") &&
                !image.getName().equals("Cyborg");
    }
    
    public Collection<Image> findAllImages() {
    	return imageRepository.findAll();
    }
}
