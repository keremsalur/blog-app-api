package com.keremsalur.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keremsalur.blogapp.model.Post;

public interface PostRepository extends JpaRepository<Post, String> {

}
