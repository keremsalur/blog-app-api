package com.keremsalur.blogapp.dto.converter.bloguser;

import org.springframework.stereotype.Component;

import com.keremsalur.blogapp.dto.bloguser.BlogUserAuthorDto;
import com.keremsalur.blogapp.model.BlogUser;

@Component
public class BlogUserAuthorDtoConverter {
    
    public BlogUserAuthorDto convertToDto(BlogUser blogUser) {
        BlogUserAuthorDto blogUserAuthor = new BlogUserAuthorDto();
        blogUserAuthor.setId(blogUser.getId());
        blogUserAuthor.setUsername(blogUser.getUsername());
        return blogUserAuthor;
    }

    public BlogUser convertToEntity(BlogUserAuthorDto blogUserAuthor) {
        BlogUser blogUser = new BlogUser();
        blogUser.setId(blogUserAuthor.getId());
        blogUser.setUsername(blogUserAuthor.getUsername());
        return blogUser;
    }

}
