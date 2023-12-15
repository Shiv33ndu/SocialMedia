package com.socialMedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.repository.UserRepository;


// This Service Class will be used to ByPass the default security password creation of Spring Boot
// will let user's to use their credentials for authentication
@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	// UserDetailsService Interface gives us a method to Load a User by Username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//here username will be Email		
		UserInfo user = userRepository.findByEmail(username);
		
		if(user == null)
			throw new UsernameNotFoundException("User not found via "+ username);
		
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		//these granted authority list holds the authorities for different user's 
		// [not needed here, but we will need it to pass it to User instance as one of the args]
		
		return new User(user.getEmail(), user.getPassword(), authorities); //we will return New User Made by Spring
	}

}
