package com.keremsalur.blogapp.dto.request.post;

import com.keremsalur.blogapp.validation.NotEmptyIfPresent;

import jakarta.validation.constraints.NotBlank;

public class CreatePostRequest {

    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotEmptyIfPresent(message = "Content cannot be blank if present")
    private byte[] content;
    @NotBlank(message = "AuthorId cannot be blank")
    private String authorId;

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
    public String getAuthorId() {
        return authorId;
    }
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
