package com.socialMedia.service;

import com.socialMedia.modelEntity.Comment;

public interface CommentService {
	
	public Comment postAComment(Comment comment, Integer postId, Integer userId) throws Exception;
	
	public String deleteAComment(Integer commentId, Integer postId) throws Exception;
	
	public Comment likeAComment(Integer commentId, Integer userId) throws Exception;
	
	public Comment findCommentById(Integer commentId) throws Exception;

}
