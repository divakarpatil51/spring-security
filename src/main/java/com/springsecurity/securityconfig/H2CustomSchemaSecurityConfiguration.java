package com.springsecurity.securityconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@ConditionalOnProperty(name = "authentication-type", havingValue = "h2-custom-database-schema")
@EnableWebSecurity
public class H2CustomSchemaSecurityConfiguration extends SecurityConfiguration {

	@Autowired
	private DataSource dataSource;

	// Following method is used for Authentication purpose
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// This should be how a real world application JDBC authentication configuration
		// should look like.

		// The database should be pre-populated with the tables and users.

		// We can either
		// 1. Use our own schema. In this case, we have to configure the auth manager to
		// user our schema.
		// 2. Use the User schema which is created with withDefaultSchema() method.

		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("").authoritiesByUsernameQuery("");
	}

}
