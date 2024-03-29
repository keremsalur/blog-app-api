package com.keremsalur.blogapp.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.keremsalur.blogapp.dto.BlogUserDto;
import com.keremsalur.blogapp.dto.converter.UserDtoConverter;
import com.keremsalur.blogapp.dto.request.CreateBlogUserRequest;
import com.keremsalur.blogapp.dto.request.UpdateBlogUserRequest;
import com.keremsalur.blogapp.exception.UserNotFoundException;
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

    public BlogUserDto createUser(CreateBlogUserRequest createUserRequest) {
        BlogUser user = new BlogUser();
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(createUserRequest.getPassword());
        userRepository.save(user);
        return userDtoConverter.convert(user);
    }

    public Set<BlogUserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userDtoConverter::convert).collect(Collectors.toSet());
    }

    public BlogUserDto getUserById(@NonNull String id) {
        return userDtoConverter.convert(userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException("User not found with id: " + id)
        ));
    }

    public void deleteUserById(@NonNull String id) {
        userRepository.deleteById(id);
    }

    public BlogUserDto updateUserById(@NonNull String id, UpdateBlogUserRequest updateBlogUserRequest) {
        BlogUser user = userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException("User not found with id: " + id)
        );
        if(updateBlogUserRequest.getUsername() != null) {
            user.setUsername(updateBlogUserRequest.getUsername());
        }
        if(updateBlogUserRequest.getPassword() != null) {
            user.setPassword(updateBlogUserRequest.getPassword());
        }
        userRepository.save(user);
        return userDtoConverter.convert(user);
    }

    public BlogUserDto updateUserById(@NonNull String id, CreateBlogUserRequest createBlogUserRequest) {
        BlogUser user = userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException("User not found with id: " + id)
        );
        user.setUsername(createBlogUserRequest.getUsername());
        user.setPassword(createBlogUserRequest.getPassword());
        userRepository.save(user);
        return userDtoConverter.convert(user);
    }

}
