package com.microservices.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.microservices.auth.service.UserService;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserService customUserDetailsService;
		
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	/**
     * Prior to Spring Security 5.0 the default PasswordEncoder was NoOpPasswordEncoder 
     * which required plain text passwords.
     * 
     * Define this Bean for Legacy purpose
     * https://docs.spring.io/spring-security/site/docs/5.0.0.RELEASE/reference/htmlsingle/#pe-dpe
     * 
     * @return PasswordEncoder
     */
	
	@Bean
    public static PasswordEncoder passwordEncoder() {
          return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
		
	@Bean
    @SuppressWarnings("deprecation")
    public static NoOpPasswordEncoder pEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
         DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
         authenticationProvider.setUserDetailsService(customUserDetailsService);
         authenticationProvider.setPasswordEncoder(passwordEncoder());
         return authenticationProvider;
    }
	
	@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
       auth
       .inMemoryAuthentication()
       .withUser("mariana")
       .password("{bcrypt}$2a$10$A99HgnwfUMEG5/Cque3NwO3D2jq8ZjhA3diANAgMMTrmXveUiMGoC") //pass: sec1
       .roles("User");
    }
	
	@Override
    protected void configure(final HttpSecurity http) throws Exception {
       http
       .requestMatchers()
       .antMatchers("/hc")
       .antMatchers("/users/**")
       .antMatchers("/oauth/**")
       .and()
       .authorizeRequests()
       .antMatchers("/hc").permitAll()
       .anyRequest().authenticated()
       .and()
       .csrf()
       .and()
       .httpBasic().disable()
       .logout().permitAll();

    }
}
