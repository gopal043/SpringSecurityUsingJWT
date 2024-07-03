package com.spring.security.spring.jwt.service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.spring.security.spring.jwt.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final String SECRATE_KEY ="f53398d050ff53baede56ce647fe606bd44856a825ee7478d3ba8069df3b2bf3";
	
	public String extractUsername(String token) {
		
		return extractClaim(token, Claims::getSubject);
	}
	
	public boolean isValid(String token, UserDetails user) {
		
		String username = extractUsername(token);
		return (user.getUsername().equals(username) && isTokenExpaired(token));
	}
	
	private boolean isTokenExpaired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token,Claims::getExpiration);
	}

	public <T> T extractClaim(String token,Function<Claims,T> resolver) {
		
		Claims claims = extractAllClaims(token);
		
		return resolver.apply(claims);
		
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				   .verifyWith(getSignKey())
				   .build()
				   .parseSignedClaims(token)
				   .getPayload();
	}
	
	public String generateToken(User user) {
		
		String token = Jwts.builder()
				           .subject(user.getUsername())
				           .issuedAt(new Date())
				           .expiration(new Date(System.currentTimeMillis()))
				           .signWith(getSignKey())
				           .compact();
		return token;
	}

	private SecretKey getSignKey() {
		// TODO Auto-generated method stub
		byte[] keyBytes = Decoders.BASE64URL.decode(SECRATE_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
