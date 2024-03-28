package com.keremsalur.blogapp.dto.converter;

import org.springframework.stereotype.Component;

import com.keremsalur.blogapp.dto.PostDto;
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
}
