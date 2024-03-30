package com.keremsalur.blogapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BlogUserNotFoundException extends RuntimeException{
    public BlogUserNotFoundException(String message) {
        super(message);
    }
}
