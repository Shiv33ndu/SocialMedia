package com.socialMedia.modelEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "story_table")
public class Story {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String storyTexts;
	
	private String contentUrl;  			//this can be image or video
	
	//private LocalDateTime postedAt;					//to show story dates, from story Archive
	
	private LocalDateTime postedAtTime;  	//to auto remove the Story after 24hrs
	
	@ManyToOne	//one user posts many stories
	private UserInfo user;					//author of the Story
	
	@OneToMany //one Reel can be liked by many users
	private List<UserInfo> userLiked = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStoryTexts() {
		return storyTexts;
	}

	public void setStoryTexts(String storyTexts) {
		this.storyTexts = storyTexts;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public LocalDateTime getPostedAtTime() {
		return postedAtTime;
	}

	public void setPostedAtTime(LocalDateTime postedAtTime) {
		this.postedAtTime = postedAtTime;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public List<UserInfo> getUserLiked() {
		return userLiked;
	}

	public void setUserLiked(List<UserInfo> userLiked) {
		this.userLiked = userLiked;
	}
	
}
