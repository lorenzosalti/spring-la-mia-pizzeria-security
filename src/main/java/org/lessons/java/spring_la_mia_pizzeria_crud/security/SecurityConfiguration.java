package org.lessons.java.spring_la_mia_pizzeria_crud.security;

import org.springframework.security.config.Customizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(requests -> requests
        .requestMatchers("/pizzas/create", "/pizzas/edit/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST, "/pizzas/**").hasAuthority("ADMIN")
        .requestMatchers("/offers/create", "/offers/edit/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST, "/offers/**").hasAuthority("ADMIN")
        .requestMatchers("/ingredients", "/ingredients/**").hasAuthority("ADMIN")
        .requestMatchers("/pizzas", "/pizzas/**", "/offeers/**").hasAnyAuthority("USER", "ADMIN")
        .requestMatchers("/", "/**").permitAll())
        .formLogin(Customizer.withDefaults())
        .cors(cors -> cors.disable())
        .csrf(csrf -> csrf.disable());
    return http.build();
  }

  @Bean
  DatabaseUserDetailsService userDetailsService() {
    return new DatabaseUserDetailsService();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  @SuppressWarnings("deprecated")
  DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

}
