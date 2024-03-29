package com.keremsalur.blogapp.dto.converter;

import org.springframework.stereotype.Component;

import com.keremsalur.blogapp.dto.BlogUserDto;
import com.keremsalur.blogapp.model.BlogUser;

@Component
public class UserDtoConverter {

    public BlogUserDto convert(BlogUser user) {
        BlogUserDto userDto = new BlogUserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
