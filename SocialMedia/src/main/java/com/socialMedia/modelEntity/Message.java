package com.socialMedia.modelEntity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String text;
	
	private String contentUrl;
	
	@ManyToOne		//one user can create multiple texts but one text cant be sent by multiple user, means sender would be one
	private UserInfo user;
		
	@JsonIgnore    //to ignore nesting problem 
	@ManyToOne
	private Chat chat;
	
	private LocalDateTime textTimeStamp;
	
	
}
