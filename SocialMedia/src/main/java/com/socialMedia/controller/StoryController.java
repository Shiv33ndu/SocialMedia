package com.socialMedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.modelEntity.Story;
import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.service.StoryService;
import com.socialMedia.service.UserService;

@RestController
public class StoryController {

	@Autowired
	StoryService storyService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/user/story/create")
	public Story postStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt ) throws Exception {
		
		UserInfo user = userService.findUserByJwt(jwt);
		
		return storyService.createAStory(story, user.getId());
		
	}
	
	@GetMapping("/api/user/stories")
	public List<Story> viewStories(@RequestHeader("Authorization") String jwt) throws Exception{
		UserInfo user = userService.findUserByJwt(jwt);
		return storyService.findStoryByUserId(user.getId());
	}
	
}
