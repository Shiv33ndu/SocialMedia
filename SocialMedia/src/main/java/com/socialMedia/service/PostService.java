package com.socialMedia.service;

import java.util.List;

import com.socialMedia.modelEntity.Post;

public interface PostService {
	
	public Post createPost(Post post, Integer userId) throws Exception;

	public String deletePost(Integer postId, Integer userId) throws Exception;

	public List<Post> findPostByUserId(Integer userId) throws Exception;

	public Post findPostById(Integer postId) throws Exception;

	public List<Post> findAllPost(Integer userId) throws Exception;

	public Post savedPost(Integer postId, Integer userId) throws Exception;

	public Post likePost(Integer postId, Integer userId) throws Exception;

}
