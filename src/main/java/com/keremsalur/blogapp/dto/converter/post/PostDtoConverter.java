package com.keremsalur.blogapp.dto.converter.post;

import org.springframework.stereotype.Component;

import com.keremsalur.blogapp.dto.post.PostDto;
import com.keremsalur.blogapp.model.Post;

@Component
public class PostDtoConverter {
    
    public PostDto convertToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        return postDto;
    }
    public Post convertToEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return post;
    }
}
