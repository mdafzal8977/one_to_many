package com.example.social_media_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.social_media_app.model.User;
//@Repository not required here
public interface UserRepository extends JpaRepository<User,Integer> {

}
