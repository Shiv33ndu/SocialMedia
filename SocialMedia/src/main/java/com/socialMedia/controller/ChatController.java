package com.socialMedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.modelEntity.Chat;
import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.request.CreateChatRequest;
import com.socialMedia.service.ChatService;
import com.socialMedia.service.UserService;

@RestController
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user/chat/create")
	public Chat createChat(@RequestHeader("Authorization") String jwt,@RequestBody CreateChatRequest chatReq) throws Exception {
		
		// On Initial conversation
		// we will only create chat b/w two users if there's any exchange of text 
		
		UserInfo loggedUser     = userService.findUserByJwt(jwt);
		UserInfo toChatwithUser = userService.findUserById(chatReq.getUserId());
		
		//System.out.println("User to Chat with ID: " + chatReq.getUserId());
		
		if(chatReq.getDummyText() != null) {
			
			//System.out.println("The MSG : " + chatReq.getDummyText()+"\n Creating Chat Record");
			
			return chatService.createAChat(loggedUser, toChatwithUser);
			
		}
		
		return null;
		
	}
	
	@GetMapping("/api/user/chat")
	public List<Chat> showChatsOfUser(@RequestHeader("Authorization") String jwt) throws Exception{
		
		UserInfo loggedUser = userService.findUserByJwt(jwt);
		
		return chatService.findAllChats(loggedUser.getId());
	}
	
	@GetMapping("/api/user/chat/{chatId}")
	public Chat findChatByID(@PathVariable Integer chatId) throws Exception {
		return chatService.findChatById(chatId);
	}
	
}
