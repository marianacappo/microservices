package com.microservices.auth;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.microservices.auth.domain.Role;
import com.microservices.auth.domain.User;
import com.microservices.auth.repositories.UserRepository;

@SpringBootApplication
public class MicroserviceAuthApplication {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;
    
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAuthApplication.class, args);
	}

    @PostConstruct
    public void init(){
        
        User user = new User(
                "mariana",
                passwordEncoder.encode("password"),
                Arrays.asList(
                        new Role("ROLE_USER"),
                        new Role("ROLE_ADMIN")));

        if (userRepository.findByUsername(user.getUsername()) == null){
            userRepository.save(user);
        }
    }
}
