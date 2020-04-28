package com.springsecurity.jwt;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springsecurity.config.SecurityConfiguration;
import com.springsecurity.jwt.service.JwtUserDetailsService;

@EnableWebSecurity
@ConditionalOnProperty(name = "authentication-type", havingValue = "jwt-authentication")
public class JwtSecurityConfiguration extends SecurityConfiguration {

	private JwtUserDetailsService userDetailsService;

	private JwtRequestFilter jwtRequestFilter;

	public JwtSecurityConfiguration(JwtUserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
		this.userDetailsService = userDetailsService;
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// By default, CSRF should not be disabled but we should disable it if we want
		// to allow traffic from Postman (non-browser entities)

		// antMatcher("/authenticate") --> Should be used in case of multiple http
		// security configs. Lets say we have 2 Security Configuration classes. One
		// should be applicable for requests with prefix "/api", then we use following

		// http.csrf().disable().antMatcher("/api").authorizeRequests().anyRequest().authenticated()

		http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll().anyRequest().authenticated()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).maximumSessions(3);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
