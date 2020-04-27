package com.springsecurity.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springsecurity.jwt.service.JwtUtil;

/**
 * The Class JwtRequestFilter.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	private static final String AUTHORIZATION_HEADER = "Authorization";
	
	private final UserDetailsService userDetailsService;
	
	private final JwtUtil jwtUtil;
	
	public JwtRequestFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
		
		if(authorizationHeader != null && !authorizationHeader.isEmpty()) {
			String jwtToken = authorizationHeader.split(" ")[1];
			String userName = jwtUtil.extractUserName(jwtToken);
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			boolean isValid = jwtUtil.validateToken(jwtToken, userDetails);
			if(isValid) {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token);
			}
		}
		filterChain.doFilter(request, response);
	}

}
