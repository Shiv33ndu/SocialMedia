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

@Entity
public class Reels {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String reelCaption;
	
	private LocalDateTime postedAt;
	
	private String videoUrl;
	
	@ManyToOne	
	private UserInfo user;  //author of the Reel
	
	@OneToMany				//one reel can be Liked by Many User
	private List<UserInfo> userLiked = new ArrayList<>();
	
	@OneToMany				//one reel can have many comments 
	private List<Comment>  comments  = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReelCaption() {
		return reelCaption;
	}

	public void setReelCaption(String reelCaption) {
		this.reelCaption = reelCaption;
	}

	public LocalDateTime getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(LocalDateTime postedAt) {
		this.postedAt = postedAt;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	 	
}
