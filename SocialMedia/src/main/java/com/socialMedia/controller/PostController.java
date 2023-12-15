package com.socialMedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.config.JwtProvider;
import com.socialMedia.modelEntity.Post;
import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.response.APIResponse;
import com.socialMedia.service.PostService;
import com.socialMedia.service.UserService;

@RestController
//@RequestMapping("/postcontrol")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	//now we will return ResponseEntity<> instead of Normal Data Type Return
	
	@PostMapping("/api/user/create")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@RequestHeader("Authorization")String jwt) throws Exception{
		
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		UserInfo user = userService.findUserByEmail(email);
		
		Post createdPost = postService.createPost(post, user.getId());
		
		return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);		
	}
	
	@DeleteMapping("/api/user/posts/{postId}")
	public ResponseEntity<APIResponse> deletePost(@PathVariable Integer postId, @RequestHeader("Authorization")String jwt) throws Exception{
		
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		UserInfo user = userService.findUserByEmail(email);
		
		String message = postService.deletePost(postId, user.getId());
		
		APIResponse response = new APIResponse(message, true);
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/api/user/posts")
	public ResponseEntity<List<Post>> findPostViaUserId(@RequestHeader("Authorization")String jwt) throws Exception{
		
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		UserInfo user = userService.findUserByEmail(email);
		
		List<Post> posts = postService.findPostByUserId(user.getId());
		
		return new ResponseEntity<>(posts, HttpStatus.OK);
		
	}
	
	@GetMapping("/search/post/{postId}")
	public ResponseEntity<Post> findPostByID(@PathVariable Integer postId) throws Exception {
		
		Post post = postService.findPostById(postId);
		
		return new ResponseEntity<Post>(post, HttpStatus.OK);
		
	}
	
	//public List<Post> findAllPost(Integer userId) //for Now Keeping it Unused, will define it as per FrontEnd
	
	@PutMapping("/user/savepost/{postId}")
	public ResponseEntity<Post> savePost(@RequestHeader("Authorization")String jwt, @PathVariable Integer postId) throws Exception{
		
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		UserInfo user = userService.findUserByEmail(email);
		
		Post savedPost = postService.savedPost(postId, user.getId());
		
		return new ResponseEntity<>(savedPost, HttpStatus.OK);
		
	}
	
	@PutMapping("/post/{postId}/user/{userId}")
	public ResponseEntity<Post> likePost(@PathVariable Integer postId, @RequestHeader("Authorization")String jwt) throws Exception{
		
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		UserInfo user = userService.findUserByEmail(email);
		
		Post likedPost = postService.likePost(postId, user.getId());
		
		return new ResponseEntity<>(likedPost, HttpStatus.OK);		
	}

}
