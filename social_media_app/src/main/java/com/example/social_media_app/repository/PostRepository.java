package com.example.social_media_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.social_media_app.model.Post;

public interface PostRepository extends JpaRepository<Post,Integer> {

}
