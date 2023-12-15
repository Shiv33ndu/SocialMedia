package com.socialMedia.service;

import java.util.List;

import com.socialMedia.modelEntity.Story;

public interface StoryService {
	
	public Story createAStory(Story story, Integer userId) throws Exception;
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
