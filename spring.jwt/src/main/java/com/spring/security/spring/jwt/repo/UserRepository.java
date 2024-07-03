package com.spring.security.spring.jwt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.spring.jwt.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);
}
