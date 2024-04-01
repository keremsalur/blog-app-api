package com.keremsalur.blogapp.dto.post;

public class PostDto {
    private String id;
    private String title;
    private byte[] content;

    public PostDto() {}

    public PostDto(String id, String title, byte[] content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    
}
