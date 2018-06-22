package com.microservices.greeting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Run as a Micro-service registering with the Eureka Discovery server
 *
 */

@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
public class GreetingServer {
	
	protected Logger logger = LoggerFactory.getLogger(GreetingServer.class);
	
    public static void main( String[] args )
    {
    	SpringApplication.run(GreetingServer.class, args);
    }
}
