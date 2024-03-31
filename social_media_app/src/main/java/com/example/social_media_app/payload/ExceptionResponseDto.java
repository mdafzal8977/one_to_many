package com.example.social_media_app.payload;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseDto {
	private LocalDateTime timestamp;
	private String message;

}
