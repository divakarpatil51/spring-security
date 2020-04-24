package com.springsecurity.inmemory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.springsecurity.config.SecurityConfiguration;

/**
 * The configuration class for in-memory security authentication.
 */
@EnableWebSecurity
@ConditionalOnProperty(name = "authentication-type", havingValue = "in-memory")
public class InMemorySecurityConfiguration extends SecurityConfiguration {

	// Following method is used for Authentication purpose
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("user").roles(ROLE_USER).and().withUser("admin")
				.password("admin").roles(ROLE_ADMIN);
	}

}
