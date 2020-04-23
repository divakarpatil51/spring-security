package com.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	protected static final String ROLE_ADMIN = "ADMIN";
	protected static final String ROLE_USER = "USER";

	// Following method is used for authorization purpose
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// If the URL in antmatcher matches the incoming request URL, the other
		// antmatchers are skipped. Hence, put the most restrictive URLs at the top
		http.authorizeRequests().antMatchers("/admin").hasRole(ROLE_ADMIN).antMatchers("/user").hasAnyRole(ROLE_USER, ROLE_ADMIN)
				.antMatchers("/").permitAll().and().formLogin();
		//There are two things in authorization:
		//1. URL level
		//2. Method level
	}

	//TODO: Use a valid password encoder.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
