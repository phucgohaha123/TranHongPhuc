package com.example.springdata.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.springdata.service.UsersService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
  @Autowired
  UsersService usersService;
  
  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  http
	    .antMatcher("/")
	        .authorizeRequests()
	            .antMatchers(HttpMethod.POST, "/sign-up").permitAll()
	            .anyRequest().hasRole("USER");
  }
}