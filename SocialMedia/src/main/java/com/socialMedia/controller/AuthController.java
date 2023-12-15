package com.socialMedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.config.JwtProvider;
import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.request.LoginRequest;
import com.socialMedia.response.AuthResponse;
import com.socialMedia.service.CustomUserDetailService;
import com.socialMedia.service.UserService;

//all the Authorization related HTTP request will pass through this to AppConfig file

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	CustomUserDetailService customUserDetail;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/signup")   //to register user into DB
	public AuthResponse userRegistration(@RequestBody UserInfo registerUser) throws Exception {
		
		UserInfo createdUser = userService.RegisterUser(registerUser);		
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(createdUser.getEmail(), createdUser.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse(token, "Succesfully Registered");
		
		return authResponse;
				
	}
	
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		
		//we will authenticate 
		
		Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse(token, "Succesfully Logged-In");
		
		return authResponse;
		
	}

	private Authentication authenticate(String email, String password) {
		
		//we will need to use CustomUserDetails Class to cross check the avaialability of given credential
		// if the credentials match, means User can Login, else no
		
		UserDetails userDetail = customUserDetail.loadUserByUsername(email);
		
		if(userDetail == null)
			throw new BadCredentialsException("Incorrect Username!");
		if(!passwordEncoder.matches(password, userDetail.getPassword()))
			throw new BadCredentialsException("Incorrect Password!");
				
		return new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
			
	}
	
	

}
