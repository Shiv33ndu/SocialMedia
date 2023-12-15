package com.socialMedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.service.UserService;

@RestController
//@RequestMapping("/usercontrol")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	
	
	@GetMapping("/api/user/id/{userId}")
	public UserInfo findUserByID(@PathVariable Integer userId) throws Exception{
		
		return userService.findUserById(userId);
		
	}
	
	@GetMapping("/api/user/email/{email}")
	public UserInfo findUserByEmail(@PathVariable String email) throws Exception {
		
		return userService.findUserByEmail(email);
		
	}
	
	@GetMapping("/api/user/follow/{userIdB}") //follow Updates 
	public UserInfo followUserMethod(@RequestHeader("Authorization")String jwt, @PathVariable Integer userIdB) throws Exception {
		
		UserInfo loggedInUser = userService.findUserByJwt(jwt);
		
		return userService.followUser(loggedInUser.getId(), userIdB);
		
	}
	
	@GetMapping("/api/user/search")
	public List<UserInfo> searchUser(@RequestParam String query){
		
		return userService.searchUser(query);
		
	}
	
	@GetMapping("/api/users")
	public List<UserInfo> viewAllUsers(){
		return userService.findAllUser();
	}
	
	@GetMapping("/api/user/profile")
	public UserInfo getUserFromToken(@RequestHeader("Authorization")String jwt) throws Exception {
		
		// ---------------------------------: The whole point of this is :---------------------------------------
		// Once a user logs in he get's a token that is now the replacement of Default Spring Key we were getting
		// while implementing the Spring Security, now these tokens are unique per User Logged-in
		// and all the secured End-points of the API can be accessed by those who have these unique Token, means
		// the user's who are logged-in, so now we need to access this Token to extract the current user's info
		
		// 1. we will extract email from the Token
		// 2. we will get the UserInfo on the basis of the Email from the Token
		
		//System.out.println("User's Token : { "+ jwt + " }"); //we will get the token published on the PostMan end
		
		// Step 1 & 2 are performed in the findUserByJwt() method in the UserService Class
		
		UserInfo user = userService.findUserByJwt(jwt);
		
		user.setPassword(null); // we dont want to show the password on front-end side, as its sensitive information
		
		return user;
		
	}
	
	@PostMapping("/api/user/updateprofile")
	public UserInfo updateUserbyJwt(@RequestHeader("Authorization")String jwt,@RequestBody UserInfo updateUser) throws Exception {
		
		//we will fetch the user ID by JWT and then by the ID we will update the new updates in the current User
		
		UserInfo user = userService.findUserByJwt(jwt);
		
		// now we will get the ID and push it into a update user function made in User Service 
		
		UserInfo updatedUser = userService.updateUserDetail(updateUser, user.getId());
		
		return updatedUser;
		
	}

}
