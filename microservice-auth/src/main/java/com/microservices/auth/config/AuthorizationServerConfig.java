package com.microservices.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${client.id}")
    private String CLIENT_ID;
    @Value("${client.secret}")
    private String CLIENT_SECRET;
    static final String PASSWORD = "password";
    static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String CLIENT_CREDENTIALS = "client_credentials";
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;

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
      clients
      .inMemory()
      .withClient(CLIENT_ID)
      .secret(passwordEncoder.encode(CLIENT_SECRET))
      .autoApprove(true)
      .authorizedGrantTypes(AUTHORIZATION_CODE, CLIENT_CREDENTIALS, PASSWORD, REFRESH_TOKEN)
      .scopes(SCOPE_READ, SCOPE_WRITE);
   }

   @Override
   public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      endpoints
      .tokenStore(new InMemoryTokenStore())
      .authenticationManager(authenticationManager);
   }

}
