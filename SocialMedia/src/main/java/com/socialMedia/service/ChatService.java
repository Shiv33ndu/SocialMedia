package com.socialMedia.service;

import java.util.List;

import com.socialMedia.modelEntity.Chat;
import com.socialMedia.modelEntity.UserInfo;

public interface ChatService {

	public Chat createAChat(UserInfo loggedUser, UserInfo toChatWithUser);
	
	public List<Chat> findAllChats(Integer loggedUserId);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
}
