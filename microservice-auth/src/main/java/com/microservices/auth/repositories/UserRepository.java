package com.microservices.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.auth.domain.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
