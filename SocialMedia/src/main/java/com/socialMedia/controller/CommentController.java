package com.socialMedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.socialMedia.modelEntity.Comment;
import com.socialMedia.modelEntity.Post;
import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.response.APIResponse;
import com.socialMedia.service.CommentService;
import com.socialMedia.service.PostService;
import com.socialMedia.service.UserService;

@RestController
public class CommentController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	PostService postService;
	
	@PostMapping("/api/post/{postId}/comment")
	public Comment postComment(@PathVariable Integer postId, 
							   @RequestHeader("Authorization")String jwt, @RequestBody Comment comment) throws Exception {
		
		UserInfo user = userService.findUserByJwt(jwt);
		
		Comment postedComment = commentService.postAComment(comment, postId, user.getId());
				
		return postedComment; //created and returned the post
		
	}
	
	@GetMapping("/api/post/{postId}/comment/{commentId}/delete")
	public ResponseEntity<APIResponse> deleteComment(@PathVariable Integer postId, 
													 @RequestHeader("Authorization")String jwt,
													 @PathVariable Integer commentId) throws Exception{
		
		UserInfo loggedInUser  = userService.findUserByJwt(jwt); 	//fetched commentAuthor or PostAuthor User 
		Post parentPost        = postService.findPostById(postId);	//fetched Parent Post
		Comment comment 	   = commentService.findCommentById(commentId); //fetched comment as well
		
		// we will allow only author of the comment to delete the comment or the Author of the Post to delete comment
		
		if( ( loggedInUser.getId() == comment.getUser().getId() ) || ( parentPost.getUser().getId() == loggedInUser.getId() ) ) {
			
			// now either the Author of the Post or Comment is loggedIn as user 
			
			String message = commentService.deleteAComment(commentId, postId);
			
			APIResponse res = new APIResponse(message, true);
			
			return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
			
		}
		
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/api/post/comment/like/{commentId}")
	public Comment likeComment(@PathVariable Integer commentId, @RequestHeader("Authorization")String jwt) throws Exception {
		
		UserInfo userWhoLiked = userService.findUserByJwt(jwt);
		
		Comment likedComment = commentService.likeAComment(commentId, userWhoLiked.getId());
		
		return likedComment;
		
	}
}
