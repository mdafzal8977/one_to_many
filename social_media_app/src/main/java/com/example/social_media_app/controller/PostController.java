package com.example.social_media_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.social_media_app.model.Post;
import com.example.social_media_app.service.PostDaoService;

@RestController
@RequestMapping("api/v1")
public class PostController {
	@Autowired
	private PostDaoService postService;
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllPosts(){
		return new ResponseEntity<List<Post>>( postService.getAllPosts(),HttpStatus.OK);
	}

}
