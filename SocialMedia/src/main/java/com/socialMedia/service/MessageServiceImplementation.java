package com.socialMedia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.modelEntity.Chat;
import com.socialMedia.modelEntity.Message;
import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.repository.ChatRepository;
import com.socialMedia.repository.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService{
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	ChatRepository chatRepository;
	
	@Autowired
	ChatService chatService;

	@Override
	public Message sendAMessage(Message message, UserInfo loggedUser, Chat chat) {

		Message newMessage = new Message();
		
		newMessage.setChat(chat);
		newMessage.setText(message.getText());
		newMessage.setUser(loggedUser);
		newMessage.setTextTimeStamp(LocalDateTime.now());
		newMessage.setContentUrl(message.getContentUrl());
				
		messageRepository.save(newMessage);
		chat.getMessages().add(newMessage);
		chatRepository.save(chat);
		
		return newMessage;
		
	}

	@Override
	public List<Message> findAllMessages(Integer chatId) throws Exception {
		
		chatService.findChatById(chatId); //this code line is just for throwing error, if no chats were found
		
		return messageRepository.findByChatId(chatId);
	}

}
