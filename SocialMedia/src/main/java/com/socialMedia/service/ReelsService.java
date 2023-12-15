package com.socialMedia.service;

import java.util.List;

import com.socialMedia.modelEntity.Reels;

public interface ReelsService {
	
	public Reels createAReel(Reels reel, Integer userId) throws Exception;
	
	public List<Reels> findReelsByUserId(Integer userId);
	
	public List<Reels> findAllReels();

}
