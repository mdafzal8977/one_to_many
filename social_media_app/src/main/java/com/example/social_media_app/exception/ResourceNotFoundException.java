package com.example.social_media_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String resourceName,String fieldName,int id) {
		super(String.format("%s not found with %s:%d",resourceName,fieldName,id));
	}

}
