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
import com.socialMedia.modelEntity.Message;
import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.service.ChatService;
import com.socialMedia.service.MessageService;
import com.socialMedia.service.UserService;

@RestController
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ChatService chatService;

	@PostMapping("/api/user/chat/{chatId}/sendmessage")
	public Message sendMessage(@RequestHeader("Authorization") String jwt, 
							   @PathVariable Integer chatId, 
							   @RequestBody Message message) throws Exception {
		
		
		UserInfo loggedUser = userService.findUserByJwt(jwt);
		
		Chat chat = chatService.findChatById(chatId);
		
		Message newMessage = messageService.sendAMessage(message, loggedUser, chat);
				
		return newMessage;
		
	}
	
	@GetMapping("/api/user/chat/{chatId}/viewmessages")
	public List<Message> viewMessages(@PathVariable Integer chatId) throws Exception{
		
		return messageService.findAllMessages(chatId);
		
	}
}
