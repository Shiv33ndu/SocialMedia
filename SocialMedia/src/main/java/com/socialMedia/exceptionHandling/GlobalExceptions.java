package com.socialMedia.exceptionHandling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptions {
	
	//we will handle all the exeption Method here 
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetail> UserExceptionHandler(UserException userException, WebRequest webRequest){
		
		ErrorDetail error = new ErrorDetail();
		
		error.setErrorCode("USER ERROR");
		error.setMessage(userException.getMessage());
		error.setPath((webRequest.getDescription(false)));
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimeStamp(LocalDateTime.now());
		
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PostException.class)
	public ResponseEntity<ErrorDetail> PostExceptionHandler(PostException postExc, WebRequest webReq){
		
		ErrorDetail error = new ErrorDetail( LocalDateTime.now(),
											 HttpStatus.NOT_FOUND.value(),
											 "POST ERROR",
											 postExc.getMessage(),
											 webReq.getDescription(false));		
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CommentException.class)
	public ResponseEntity<ErrorDetail> CommmentExceptionHandler(CommentException comExc, WebRequest webReq){
		
		ErrorDetail error = new ErrorDetail( LocalDateTime.now(),
											 HttpStatus.NOT_FOUND.value(),
											 "COMMENT ERROR",
											 comExc.getMessage(),
											 webReq.getDescription(false));		
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ChatException.class)
	public ResponseEntity<ErrorDetail> ChatExceptionHandler(ChatException chatExc, WebRequest webReq){
		
		ErrorDetail error = new ErrorDetail( LocalDateTime.now(),
											 HttpStatus.NOT_FOUND.value(),
											 "CHAT ERROR",
											 chatExc.getMessage(),
											 webReq.getDescription(false));		
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
}
