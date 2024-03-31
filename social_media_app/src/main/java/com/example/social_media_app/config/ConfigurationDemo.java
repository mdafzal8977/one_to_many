package com.example.social_media_app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.social_media_app.Student;

@Configuration
public class ConfigurationDemo {
    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
    }
    @Bean
    @Scope("prototype")
    Student student() {
    	return new Student();
    }

}
