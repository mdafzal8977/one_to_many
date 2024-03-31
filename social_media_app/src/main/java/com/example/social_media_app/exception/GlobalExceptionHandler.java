package com.example.social_media_app.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.social_media_app.payload.ExceptionResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	// Generic Exception Handling
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponseDto> handleAllException(Exception ex) {
		var exceptionResponseDto = new ExceptionResponseDto(LocalDateTime.now(), ex.getMessage());
		return new ResponseEntity<ExceptionResponseDto>(exceptionResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	// Specific Exception Handling
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
		var exceptionResponseDto = new ExceptionResponseDto(LocalDateTime.now(), ex.getMessage());
		return new ResponseEntity<ExceptionResponseDto>(exceptionResponseDto, HttpStatus.NOT_FOUND);
	}

	// In case of validation error(@Past,@Size(min=2)),ResponseStatus is
	// Bad-request(400) which is fine,
	// and exception thrown is MethodArgumentNotvalidException
	// Here we are handling it to get proper exception response structure
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<ObjectError> list = ex.getAllErrors();
		String res = "Total validation errors: " + ex.getErrorCount() + "\n";
		int i = 1;
		for (ObjectError error : list) {
			res = res + i + ") " + error.getDefaultMessage() + " ";
			i++;

		}
		System.out.println(res);
		ExceptionResponseDto response = new ExceptionResponseDto(LocalDateTime.now(), res);
		return new ResponseEntity<ExceptionResponseDto>(response, HttpStatus.BAD_REQUEST);
	}

}
