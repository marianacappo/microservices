package com.microservice.secure.imagecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ImageCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageCatalogServiceApplication.class, args);
	}
}
