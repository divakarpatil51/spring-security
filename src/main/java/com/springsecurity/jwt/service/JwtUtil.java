package com.springsecurity.jwt.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * The Class JwtUtil.
 */
@Service
public class JwtUtil {

	private static final String SECRET_KEY = "SECRET_KEY_SECRET_KEY_SECRET_KEY";

	//////////////////////////JWT Token Generation////////////////////////
	public String generateToken(UserDetails userDetails) {
		return createToken(new HashMap<>(), userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claimsMap, String username) {
		return Jwts.builder().setClaims(claimsMap).setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	////////////////////////JWT Token Validation////////////////////////
	
	public boolean validateToken(String token, UserDetails userDetails) {
		return userDetails.getUsername().equals(extractUserName(token)) && !tokenExpired(token);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	private boolean tokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return claimsResolver.apply(claims);
	}
}
