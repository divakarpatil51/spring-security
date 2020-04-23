package com.springsecurity.jpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.jpa.model.User;
import com.springsecurity.jpa.repository.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> user = userRepository.findByUserName(username);
		return user.map(JpaUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
