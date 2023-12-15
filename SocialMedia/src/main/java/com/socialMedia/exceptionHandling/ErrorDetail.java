package com.socialMedia.exceptionHandling;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
	
	private LocalDateTime timeStamp;
	private int status;
	private String errorCode;
	private String message;
	private String path;
	
	

}
