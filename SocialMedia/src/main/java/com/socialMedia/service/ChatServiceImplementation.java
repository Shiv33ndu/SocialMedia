package com.socialMedia.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.constants.ErrorCodeConstants;
import com.socialMedia.exceptionHandling.ChatException;
import com.socialMedia.modelEntity.Chat;
import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService{

	@Autowired
	ChatRepository chatRepository;
	
	@Override
	public Chat createAChat(UserInfo loggedUser, UserInfo toChatWithUser) {
		
		// Before making a new chat, we will need to check 
		// if there's an existing chat containing both user's, if yes we will continue from there, else a new chat will be created
		
		Chat isExist = chatRepository.findChatByCurrentUsers(loggedUser, toChatWithUser);
		
		if(isExist != null)
			return isExist;
		
		Chat createdChat = new Chat();
		
		createdChat.getUsers().add(toChatWithUser);
		createdChat.getUsers().add(loggedUser);
		createdChat.setTimeStamp(LocalDateTime.now());
		
		return chatRepository.save(createdChat);
	}

	@Override
	public List<Chat> findAllChats(Integer loggedUserId) {
		
		return chatRepository.findChatByUsersId(loggedUserId);
	}

	@Override
	public Chat findChatById(Integer chatId) throws ChatException {
		
		Optional<Chat> opt = chatRepository.findById(chatId);
		
		if(opt.isEmpty()) throw new ChatException(ErrorCodeConstants.CHAT_NOT_FOUND+ chatId);
		
		return opt.get();
	}

	
	
}
