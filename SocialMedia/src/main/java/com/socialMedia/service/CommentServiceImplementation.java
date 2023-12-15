package com.socialMedia.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.constants.ErrorCodeConstants;
import com.socialMedia.exceptionHandling.CommentException;
import com.socialMedia.modelEntity.Comment;
import com.socialMedia.modelEntity.Post;
import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.repository.CommentRepository;
import com.socialMedia.repository.PostRepository;

@Service
public class CommentServiceImplementation implements CommentService{
	
	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	CommentRepository commentRepository;

	@Override
	public Comment postAComment(Comment comment, Integer postId, Integer userId) throws Exception {

		// we will post a new comment in a Post with given userId
		
		UserInfo authUser = userService.findUserById(userId);  //we will get the Author's ID
		
		Post postOfComment = postService.findPostById(postId);  //we fetched post too, on which comment will be added
		
		//now we will set the Comment and it's details into CommentModal Entity
		
		//new Comment();
		
		comment.setCommentedAt(LocalDateTime.now());
		comment.setUser(authUser); 			       // added Author of the Post into Comment
		comment.setContent(comment.getContent());
		
		Comment postedComment = commentRepository.save(comment);
		
		postOfComment.getComments().add(comment);  // added comment into the Parent Post
		postRepository.save(postOfComment);		   // saved & updated the Post in DB 
		
		
		
		return postedComment;	   // saved the Comment into DB
	}

	@Override
	public String deleteAComment(Integer commentId, Integer postId) throws Exception {
		
		// now we will delete an existing comment from a post 
		
		//UserInfo authUser = userService.findUserById(userId);  //we will get the Author's ID
		
		Post postOfComment = postService.findPostById(postId);  //we fetched post too, to reflect the update on 
				
		
		if(commentRepository.findById(commentId).isEmpty())
			throw new CommentException(ErrorCodeConstants.COMMENT_NOT_FOUND);
		
		// comment exists, now deleting the comment by ID
		
		Comment comment = commentRepository.findById(commentId).get();
		
		// first, removing the comment from Parent Post
		
		if(postOfComment.getComments().contains(comment))
			postOfComment.getComments().remove(comment);	//removed the comment from Parent Post
		
		postRepository.save(postOfComment);					//post will be updated to reflect new list of comments
		
		commentRepository.delete(comment); 					//removed the comment from DB
		
		return "Comment Deleted!";
	}

	@Override
	public Comment likeAComment(Integer commentId, Integer userId) throws Exception {

		// to make this work, we will add users into LikedComment by User List 
		
		UserInfo userWhoLiked  = userService.findUserById(userId);//we will get the id of the user who liked this comment
		
		Comment likedComment = findCommentById(commentId); 	  //fetched the LikedComment by the user 
		
		if(likedComment.getUserLiked().contains(userWhoLiked))
			likedComment.getUserLiked().remove(userWhoLiked); //if it's double click, then liked will be removed
		
		else
			likedComment.getUserLiked().add(userWhoLiked);    //if it's single click, then like will be added
		
		//updating the comment for the either of the actions above 
		commentRepository.save(likedComment);
		
		return null;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws CommentException {
		
		Optional<Comment> opt = commentRepository.findById(commentId);
		
		if(opt.isEmpty())
			throw new CommentException(ErrorCodeConstants.COMMENT_NOT_FOUND);
		
		return opt.get();			//returned the found comment by commentId
	}

}
