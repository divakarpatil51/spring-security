package com.springsecurity.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping
	public String getText() {
		return "<h1>Welcome!</h1>";
	}
	
	@RolesAllowed(value = {"ADMIN", "USER"})
	@GetMapping("/user")
	public String user() {
		return "<h1>Welcome User!</h1>";
	}
	
	@RolesAllowed(value = "ADMIN")
	@GetMapping("/admin")
	public String admin() {
		return "<h1>Welcome Admin!</h1>";
	}
}
