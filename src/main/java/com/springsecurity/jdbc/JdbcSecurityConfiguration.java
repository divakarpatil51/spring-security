package com.springsecurity.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.springsecurity.config.SecurityConfiguration;

/**
 * The configuration class for jdbc security authentication.
 */
@EnableWebSecurity
@ConditionalOnProperty(name = "authentication-type", havingValue = "jdbc-authentication")
public class JdbcSecurityConfiguration extends SecurityConfiguration {

	@Autowired
	private DataSource dataSource;

	// Following method is used for Authentication purpose
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// There are two ways of setting up JDBC schemas in spring security:

		// 1. JDBC Default Schema - This option creates default tables (User and
		// Authorities) for us in db.

		// USAGE:
		// auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
		// .withUser(User.withUsername("admin").password("admin").roles("ADMIN"))
		// .withUser(User.withUsername("user").password("user").roles("USER"))

		////////////////////////////////////////////////////////////////////////////////////////////////

		// 2. JDBC custom schema -
		// This should be how a real world application JDBC authentication configuration
		// should look like.
		// The database should be pre-populated with the tables and users.

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, active from users where username = ?")
				.authoritiesByUsernameQuery(
						"select roles.id, role from roles\r\n" + "join users_roles on roles_id = roles.id\r\n"
								+ "join users on user_id = users.id\r\n" + "where users.username = ?");
	}
	
}
