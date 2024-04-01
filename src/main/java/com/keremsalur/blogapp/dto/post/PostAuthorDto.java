package com.keremsalur.blogapp.dto.post;

import com.keremsalur.blogapp.dto.bloguser.BlogUserAuthorDto;

public class PostAuthorDto {
    private BlogUserAuthorDto author;
    private String title;
    private byte[] content;

    public PostAuthorDto() {}

    public PostAuthorDto(BlogUserAuthorDto author, String title, byte[] content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public BlogUserAuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(BlogUserAuthorDto author) {
        this.author = author;
    }

    


}
