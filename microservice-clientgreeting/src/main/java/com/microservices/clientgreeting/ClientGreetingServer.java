package com.microservices.clientgreeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.microservices.clientgreeting.controller.ClientGreetingController;
import com.microservices.clientgreeting.service.ClientGreetingService;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientGreetingServer {
	
    public static void main( String[] args )
    {
    	SpringApplication.run(ClientGreetingServer.class, args);
    }
    
    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
    	return new RestTemplate();
    }
}
