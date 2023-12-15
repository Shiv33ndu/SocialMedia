package com.socialMedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialMedia.modelEntity.Reels;

@Repository
public interface ReelsRepository extends JpaRepository<Reels, Integer> {
	
	List<Reels> findReelsByUserId(Integer userId);

}
