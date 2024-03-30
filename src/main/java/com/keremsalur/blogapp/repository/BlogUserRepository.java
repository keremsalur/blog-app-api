package com.keremsalur.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keremsalur.blogapp.model.BlogUser;

public interface BlogUserRepository extends JpaRepository<BlogUser, String> {

}
