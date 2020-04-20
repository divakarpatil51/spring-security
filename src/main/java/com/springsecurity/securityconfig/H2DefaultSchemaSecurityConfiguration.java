package com.springsecurity.securityconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;

@ConditionalOnProperty(name = "authentication-type", havingValue = "h2-default-schema")
@EnableWebSecurity
public class H2DefaultSchemaSecurityConfiguration extends SecurityConfiguration {
	
	@Autowired
	private DataSource dataSource;

	// Following method is used for Authentication purpose
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 1. Since, we have H2 database in our classpath, Spring will automatically
		// inject that as datasource for us.
		// 2. withDefaultSchema() --> This option creates default tables (User and
		// Authorities) for us in db.
		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
				.withUser(User.withUsername("admin").password("admin").roles("ADMIN"))
				.withUser(User.withUsername("user").password("user").roles("USER"));
	}

}
