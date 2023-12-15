package com.socialMedia.modelEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String content;
	
	private LocalDateTime commentedAt;
	
	@ManyToMany
	private List<UserInfo> userLiked = new ArrayList<>();
	
	@ManyToOne
	private UserInfo user;    //the author user of the comment

	public Comment() {
		
	}
	
	public Comment(Integer id, String content, LocalDateTime commentedAt, List<UserInfo> userLiked) {
		super();
		this.id = id;
		this.content = content;
		this.commentedAt = commentedAt;
		this.userLiked = userLiked;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCommentedAt() {
		return commentedAt;
	}

	public void setCommentedAt(LocalDateTime commentedAt) {
		this.commentedAt = commentedAt;
	}

	public List<UserInfo> getUserLiked() {
		return userLiked;
	}

	public void setUserLiked(List<UserInfo> userLiked) {
		this.userLiked = userLiked;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	} 
	
}
