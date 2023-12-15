package com.socialMedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialMedia.modelEntity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
	
	List<Message> findByChatId(Integer chatId);

}
