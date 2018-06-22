package com.microservice.secure.imagecatalogservice.init;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.microservice.secure.imagecatalogservice.domain.Image;
import com.microservice.secure.imagecatalogservice.repositories.ImageRepository;

@Component
public class ImageInitializer implements CommandLineRunner {

	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Stream.of("Wonder Woman", "Iron Man", "Superman", "Batman", "Black Panther", "Flash", "Cyborg")
				.forEach(image -> imageRepository.save(new Image(image)));
		
		imageRepository.findAll().forEach(System.out::println);
	}
	
}
