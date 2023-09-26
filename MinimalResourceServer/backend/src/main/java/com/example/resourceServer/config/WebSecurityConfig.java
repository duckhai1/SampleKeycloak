package com.example.resourceServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private final JwtAuthConverter jwtAuthConverter;

  public WebSecurityConfig(JwtAuthConverterProperties properties) {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    this.jwtAuthConverter = new JwtAuthConverter(jwtGrantedAuthoritiesConverter, properties);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
        .antMatchers("/home").hasAnyRole("FIS_user", "FIS_admin")
        .antMatchers("/admin").hasRole("FIS_admin")
        .anyRequest()
        .authenticated();
    http.oauth2ResourceServer()
        .jwt()
        .jwtAuthenticationConverter(jwtAuthConverter);
    http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.cors().and().csrf().disable();
    return http.build();
  }
}
