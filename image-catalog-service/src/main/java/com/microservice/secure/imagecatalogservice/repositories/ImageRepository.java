package com.microservice.secure.imagecatalogservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.microservice.secure.imagecatalogservice.domain.Image;

@RepositoryRestResource
public interface ImageRepository extends JpaRepository<Image, Long>{
}
