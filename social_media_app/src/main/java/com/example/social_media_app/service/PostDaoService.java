package com.example.social_media_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.social_media_app.exception.ResourceNotFoundException;
import com.example.social_media_app.model.Post;
import com.example.social_media_app.model.User;
import com.example.social_media_app.repository.PostRepository;
import com.example.social_media_app.repository.UserRepository;

@Component
public class PostDaoService {
	@Autowired 
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;
	//Creating post for a particular user
	public Post createPost(int user_id,Post post) {
		User user=userRepository.findById(user_id).orElseThrow(()->new ResourceNotFoundException("User","Id",user_id));
		post.setUser(user);
		return postRepository.save(post);
		
	}
	//GettingAllPosts
	public List<Post> getAllPosts(){
		return postRepository.findAll();
		
	}

}
