package com.keremsalur.blogapp.dto.request;

import com.keremsalur.blogapp.validation.NotEmptyIfPresent;

public class PatchBlogUserRequest {

    @NotEmptyIfPresent(message = "Username cannot be blank if present")
    private String username;
    @NotEmptyIfPresent(message = "Password cannot be blank if present")
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
