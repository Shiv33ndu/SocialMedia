package com.socialMedia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.modelEntity.Reels;
import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.repository.ReelsRepository;

@Service
public class ReelsServiceImplementation implements ReelsService{
	
	@Autowired
	ReelsRepository reelsRepository;
	
	@Autowired
	UserService userService;

	@Override
	public Reels createAReel(Reels reel, Integer userId) throws Exception {

		Reels createdReel = new Reels();
		
		UserInfo user = userService.findUserById(userId);
		
		createdReel.setReelCaption(reel.getReelCaption());
		createdReel.setUser(user);
		createdReel.setVideoUrl(reel.getVideoUrl());
		createdReel.setPostedAt(LocalDateTime.now());
		
		return reelsRepository.save(createdReel);
	}

	@Override
	public List<Reels> findReelsByUserId(Integer userId) {
		
		return reelsRepository.findReelsByUserId(userId);
	}

	@Override
	public List<Reels> findAllReels() {
		return reelsRepository.findAll();
	}

}
