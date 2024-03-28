package com.keremsalur.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keremsalur.blogapp.model.BlogUser;

public interface UserRepository extends JpaRepository<BlogUser, String> {

}
