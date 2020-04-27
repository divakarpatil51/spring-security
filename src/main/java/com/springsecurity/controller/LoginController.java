package com.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.jwt.model.AuthenticationRequest;
import com.springsecurity.jwt.service.JwtUtil;

@RestController
public class LoginController {

	private final AuthenticationManager authenticationManager;

	private final UserDetailsService userDetailsService;

	private final JwtUtil jwtUtil;

	public LoginController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
			JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());

		String jwtToken = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(jwtToken);
	}
}
