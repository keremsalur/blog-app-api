package com.keremsalur.blogapp.dto.converter;

import org.springframework.stereotype.Component;

import com.keremsalur.blogapp.dto.UserDto;
import com.keremsalur.blogapp.model.BlogUser;

@Component
public class UserDtoConverter {

    public UserDto convert(BlogUser user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
