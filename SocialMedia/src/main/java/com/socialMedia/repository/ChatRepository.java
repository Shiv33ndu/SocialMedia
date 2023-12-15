package com.socialMedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.socialMedia.modelEntity.Chat;
import com.socialMedia.modelEntity.UserInfo;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer>{
	
	List<Chat> findChatByUsersId(Integer usersId);
	
	@Query("SELECT c from Chat c WHERE :user1 MEMBER OF c.users AND :user2 MEMBER OF c.users")
	Chat findChatByCurrentUsers(@Param("user1") UserInfo user1,@Param("user2") UserInfo user2);

}
