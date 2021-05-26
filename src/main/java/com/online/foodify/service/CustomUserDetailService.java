package com.online.foodify.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.online.foodify.model.CustomUserDetails;
import com.online.foodify.model.User;
import com.online.foodify.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user=userRepo.findUserByEmail(email);
		user.orElseThrow(()-> new UsernameNotFoundException("Email is not Valid"));
		return user.map(CustomUserDetails::new).get();
	}
	
}
