package com.socialMedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.modelEntity.Reels;
import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.service.ReelsService;
import com.socialMedia.service.UserService;

@RestController
public class ReelsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReelsService reelsService;
	
	@PostMapping("/api/reel/create")
	public Reels createReel(@RequestHeader("Authorization")String jwt, @RequestBody Reels reel) throws Exception {
		
		UserInfo user = userService.findUserByJwt(jwt);
		
		Reels createdReel = reelsService.createAReel(reel, user.getId());
		
		return createdReel;
		
	}
	
	@GetMapping("/api/reels")
	public List<Reels> viewAllReels(){
		return reelsService.findAllReels();
	}
	
	@GetMapping("/api/user/reels")
	public List<Reels> viewUserReels(@RequestHeader("Authorization") String jwt) throws Exception{
		
		UserInfo user = userService.findUserByJwt(jwt);
		
		return reelsService.findReelsByUserId(user.getId());
		
	}

}
