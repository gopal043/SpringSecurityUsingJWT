package com.spring.security.spring.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.spring.jwt.model.AuthenticationResponse;
import com.spring.security.spring.jwt.model.User;
import com.spring.security.spring.jwt.service.AuthenticationService;

@RestController
public class AuthenticationController {

	AuthenticationService authenticationService;
	
	ResponseEntity<AuthenticationResponse> register(
			@RequestBody User req){
		return ResponseEntity.ok(authenticationService.register(req));
	}
	
	ResponseEntity<AuthenticationResponse> login(
			@RequestBody User req){
		return ResponseEntity.ok(authenticationService.register(req));
	}
	
}
