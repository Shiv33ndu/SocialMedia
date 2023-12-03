package com.socialMedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@PutMapping("/user/register")   //to register user into DB
	public UserInfo userRegistration(@RequestBody UserInfo registerUser) {
		
		return userService.RegisterUser(registerUser);		
				
	}
	
	@GetMapping("/user/{userId}")
	public UserInfo findUserByID(@PathVariable Integer userId) throws Exception{
		
		return userService.findUserById(userId);
		
	}
	
	@GetMapping("/user/{email}")
	public UserInfo findUserByEmail(@PathVariable String email) {
		
		return userService.findUserByEmail(email);
		
	}
	
	@GetMapping("/user/follow/{userIdA}/{userIdB}") //follow Updates 
	public UserInfo followUpdate(@PathVariable Integer userIdA, @PathVariable Integer userIdB) throws Exception {
		
		return userService.followUser(userIdA, userIdB);
		
	}
	
	@GetMapping("/user/search")
	public List<UserInfo> searchUser(@RequestParam String query){
		
		return userService.searchUser(query);
		
	}

}
