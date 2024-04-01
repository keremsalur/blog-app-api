package com.keremsalur.blogapp.dto.converter.bloguser;

import org.springframework.stereotype.Component;

import com.keremsalur.blogapp.dto.bloguser.BlogUserDto;
import com.keremsalur.blogapp.model.BlogUser;

@Component
public class BlogUserDtoConverter {

    public BlogUserDto convertToDto(BlogUser user) {
        BlogUserDto userDto = new BlogUserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public BlogUser convertToEntity(BlogUserDto userDto) {
        BlogUser user = new BlogUser();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }

}
