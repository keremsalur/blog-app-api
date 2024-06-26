package com.keremsalur.blogapp.dto.bloguser;

public class BlogUserAuthorDto {

    private String id;
    private String username;

    public BlogUserAuthorDto() {}

    public BlogUserAuthorDto(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
