package com.socialMedia.modelEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data			//lombok for Getter Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String chatName;
	
	private String chatThumbnailImage;
	
	private LocalDateTime timeStamp;
	
	@ManyToMany				//one chat will have multiple users and all users can have multiple chats
	private List<UserInfo> users = new ArrayList<>();
	
	@OneToMany(mappedBy = "chat")	//one Chat can have multiple Messages //mapped by is to prevent create another table "message_chat"
	private List<Message> messages = new ArrayList<>();
	
	
}
