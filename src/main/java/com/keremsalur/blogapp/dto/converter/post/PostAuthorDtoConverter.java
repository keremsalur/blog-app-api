package com.keremsalur.blogapp.dto.converter.post;

import org.springframework.stereotype.Component;

import com.keremsalur.blogapp.dto.converter.bloguser.BlogUserAuthorDtoConverter;
import com.keremsalur.blogapp.dto.post.PostAuthorDto;
import com.keremsalur.blogapp.model.Post;

@Component
public class PostAuthorDtoConverter {

    private final BlogUserAuthorDtoConverter blogUserAuthorDtoConverter;

    public PostAuthorDtoConverter(BlogUserAuthorDtoConverter blogUserAuthorDtoConverter) {
        this.blogUserAuthorDtoConverter = blogUserAuthorDtoConverter;
    }


    public PostAuthorDto convertToDto(Post post) {
        PostAuthorDto postAuthorDto = new PostAuthorDto();
        postAuthorDto.setTitle(post.getTitle());
        postAuthorDto.setContent(post.getContent());
        postAuthorDto.setAuthor(blogUserAuthorDtoConverter.convertToDto(post.getAuthor()));
        return postAuthorDto;
    }
}
