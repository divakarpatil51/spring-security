package com.springsecurity.securityconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@ConditionalOnProperty(name = "authentication-type", havingValue = "in-memory")
@EnableWebSecurity
public class InMemorySecurityConfiguration extends SecurityConfiguration {

	// Following method is used for Authentication purpose
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("user").roles(ROLE_USER).and().withUser("admin")
				.password("admin").roles(ROLE_ADMIN);
	}

}
