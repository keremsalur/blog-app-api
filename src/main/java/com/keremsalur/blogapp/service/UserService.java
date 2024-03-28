package com.keremsalur.blogapp.service;

import org.springframework.stereotype.Service;

import com.keremsalur.blogapp.dto.UserDto;
import com.keremsalur.blogapp.dto.converter.UserDtoConverter;
import com.keremsalur.blogapp.dto.request.CreateUserRequest;
import com.keremsalur.blogapp.model.BlogUser;
import com.keremsalur.blogapp.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public UserDto createUser(CreateUserRequest createUserRequest) {
        BlogUser user = new BlogUser();
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(createUserRequest.getPassword());
        userRepository.save(user);
        return userDtoConverter.convert(user);
    }

}
