package com.socialMedia.service;

import java.util.List;

import com.socialMedia.modelEntity.Chat;
import com.socialMedia.modelEntity.Message;
import com.socialMedia.modelEntity.UserInfo;

public interface MessageService {
	
	public Message sendAMessage(Message message, UserInfo loggedUser, Chat chat);
	
	public List<Message> findAllMessages(Integer chatId) throws Exception;
	
	

}
