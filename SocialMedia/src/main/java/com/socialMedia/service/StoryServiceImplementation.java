package com.socialMedia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.modelEntity.Story;
import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.repository.StoryRepository;

@Service
public class StoryServiceImplementation implements StoryService{

	@Autowired
	UserService userService;
	
	@Autowired
	StoryRepository storyRepository;
	
	@Override
	public Story createAStory(Story story, Integer userId) throws Exception {

		Story createdStory = new Story();
		
		UserInfo user = userService.findUserById(userId);
		
		createdStory.setContentUrl(story.getContentUrl());
		createdStory.setPostedAtTime(LocalDateTime.now());
		createdStory.setUser(user);		
		createdStory.setStoryTexts( (story.getStoryTexts()!= null) ?  story.getStoryTexts() : null);
		
		return storyRepository.save(createdStory);
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception {
		
		return storyRepository.findByUserId(userId);
	}

}
