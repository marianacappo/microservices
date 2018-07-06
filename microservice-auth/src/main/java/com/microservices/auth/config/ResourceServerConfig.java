package com.microservices.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Resource Server, es la API. Para acceder a los recursos el usuario debe estar autenticado
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	private static final String RESOURCE_ID = "resource_id";

	@Override
    public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
       resources.resourceId(RESOURCE_ID);
    }
	
	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/**").authenticated();
	}
	
}
