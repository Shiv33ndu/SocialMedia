package com.socialMedia.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.constants.ErrorCodeConstants;
import com.socialMedia.exceptionHandling.PostException;
import com.socialMedia.modelEntity.Post;
import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.repository.PostRepository;
import com.socialMedia.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService{
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Post createPost(Post post, Integer userId) throws Exception {
		
		//while creating post User sets caption, post can be an Image/Video, Timing of Posting
		
		Post newPost = new Post();
		
		newPost.setCaption(post.getCaption());
		
		if(post.getVideoUrl() != null) {   //-- BY SHIVENDU 
			newPost.setVideoUrl(post.getVideoUrl()); //if user is posting Video then add video in post
		}
		
		newPost.setImageUrl(post.getImageUrl());
		newPost.setUser(userService.findUserById(userId)); //we added the whole UserInfo Object with Post 
		newPost.setPostedAt(ZonedDateTime.now(TimeZone.getTimeZone("Asia/Kolkata").toZoneId())); //Indian Time Zone w/o formatting
		
		postRepository.save(newPost);
		
		return newPost;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws PostException {
		
		//we will check if the Post exists with the Post ID with correct User ID
		//this will be later on Sorted by Spring Security [WILL BE REMOVED]
		
		Optional<Post> post = postRepository.findById(postId);
		
		if(post.isEmpty()) {
			
			throw new PostException(ErrorCodeConstants.POST_NOT_FOUND + postId);
		}
		
		Post foundPost = post.get(); //we found the post and extracted it into foundPost 
		
		//we will check if the correct user is trying to delete it, Later On this logic will be removed [WILL BE REMOVED]
		
		if(foundPost.getUser().getId() == userId) {
			//user is owner of the post and can manipulate it 
			postRepository.delete(foundPost);
			return "Post deleted with ID: "+ postId;
		}
		
		return null;
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) throws PostException {
		
		if(postRepository.findPostByUserId(userId) == null)
			throw new PostException(ErrorCodeConstants.POST_NOT_FOUND_USERID + userId);
		
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws PostException {
		
		Optional<Post> post = postRepository.findById(postId);
		
		if(post.isEmpty())
			throw new PostException(ErrorCodeConstants.POST_NOT_FOUND + postId);
		
		Post foundPost = post.get();
		
		return foundPost;
	}

	@Override
	public List<Post> findAllPost(Integer userId) throws PostException {   //this method might get removed 
		return findPostByUserId(userId);
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {
		
		//System.out.println("IN SAVED POST METHOD BODY \n\n\n");
		
		UserInfo user = userService.findUserById(userId);
		//System.out.println("User ID: "+ user.getId());
		//System.out.println("findind Post with ID : " + postId);
		Post savedPost= findPostById(postId);
				
		System.out.println("SavedPost ID : " + savedPost.getPostId()+ "\n\n\n");
		
		if(user.getSavedPost().contains(savedPost)) {
			user.getSavedPost().remove(savedPost); //we are simulating the like dislike post effect by same button
			System.out.println("Post Removed ID : "+ savedPost.getPostId());
		}
		else {
			
			user.getSavedPost().add(savedPost);
			System.out.println("Post Saved ID : "+ savedPost.getPostId());
		}
		userRepository.save(user);
		
		return savedPost; //for now we will return the SavedPosts info 
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		
		Post post 	  = findPostById(postId);
		UserInfo user = userService.findUserById(userId); 
		
		if(post.getUserLiked().contains(user)) {
			//if user double click the liked post, it will be un-liked
			post.getUserLiked().remove(user);
		}
		else
			post.getUserLiked().add(user);
		
		
		return postRepository.save(post);
	}
	
}
