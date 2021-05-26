package com.online.foodify.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.foodify.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	Optional<User> findUserByEmail(String email);
}
