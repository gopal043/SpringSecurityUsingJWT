package com.spring.security.spring.jwt.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.security.spring.jwt.model.AuthenticationResponse;
import com.spring.security.spring.jwt.model.User;
import com.spring.security.spring.jwt.repo.UserRepository;

public class AuthenticationService {

	UserRepository repository;

	PasswordEncoder passwordEncoder;

	JwtService jwtService;

	AuthenticationManager authenticationManager;

	public AuthenticationResponse register(User request) {

		User user = new User();
		user.setFirstName(request.getFirstName());

		user = repository.save(user);

		String token = jwtService.generateToken(user);

		return new AuthenticationResponse(token);
	}

	AuthenticationResponse authenticate(User req) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
		User user = repository.findByUsername(req.getUsername()).orElseThrow();
		String token = jwtService.generateToken(user);
		return new AuthenticationResponse(token);
	}

}
