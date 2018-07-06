package com.microservices.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Generar tokens para un client
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private PasswordEncoder passwordEncoder;
   
   @Override
   public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
      security
      .tokenKeyAccess("permitAll()")
      .checkTokenAccess("isAuthenticated()");
   }

   @Override
   public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
       System.out.println(passwordEncoder.encode("sec1"));
       System.out.println(passwordEncoder.encode("{noop}sec1"));
      clients
      .inMemory()
      .withClient("ABC")
      .secret(passwordEncoder.encode("sec1"))
      .autoApprove(true)
      .authorizedGrantTypes("authorization_code", "client_credentials", "password", "refresh_token")
      .scopes("read", "write")
      .redirectUris("http://localhost:8080/oauth/token");
   }

   @Override
   public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      endpoints
      .tokenStore(new InMemoryTokenStore())
      .authenticationManager(authenticationManager);
   }

}
