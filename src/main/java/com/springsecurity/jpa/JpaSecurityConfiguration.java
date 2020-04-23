package com.springsecurity.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.springsecurity.config.SecurityConfiguration;

@EnableWebSecurity
@ConditionalOnProperty(name = "authentication-type", havingValue = "db-authentication")
public class JpaSecurityConfiguration extends SecurityConfiguration {

	@Autowired
	private UserDetailsService userDetailsService;

	// Following method is used for Authentication purpose
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// If we have our schema, then we should be overriding UserDetailsService class
		// and populating UserDetails object with our user's data which will be
		// internally called by AuthenticationProvider class.
		auth.userDetailsService(userDetailsService);
	}

}
