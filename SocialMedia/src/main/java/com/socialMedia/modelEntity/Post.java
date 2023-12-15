package com.socialMedia.modelEntity;

import java.time.ZonedDateTime;
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
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	
	private String caption;
	
	private String imageUrl;
	
	private String videoUrl;
	
	
	@ManyToOne			//many posts Posted by One user
	private UserInfo user;
	
	private ZonedDateTime postedAt;
	
	@OneToMany			//one post will be liked by many users
	private List<UserInfo> userLiked = new ArrayList<>();
	
	@OneToMany			//one post will have many comments
	private List<Comment> comments   = new ArrayList<>();

	public List<UserInfo> getUserLiked() {
		return userLiked;
	}

	public void setUserLiked(List<UserInfo> userLiked) {
		this.userLiked = userLiked;
	}

	public ZonedDateTime getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(ZonedDateTime postedAt) {
		this.postedAt = postedAt;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
}
