package com.keremsalur.blogapp.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.keremsalur.blogapp.dto.bloguser.BlogUserDto;
import com.keremsalur.blogapp.dto.converter.bloguser.BlogUserDtoConverter;
import com.keremsalur.blogapp.dto.request.bloguser.CreateBlogUserRequest;
import com.keremsalur.blogapp.dto.request.bloguser.PatchBlogUserRequest;
import com.keremsalur.blogapp.dto.request.bloguser.UpdateBlogUserRequest;
import com.keremsalur.blogapp.exception.BlogUserNotFoundException;
import com.keremsalur.blogapp.model.BlogUser;
import com.keremsalur.blogapp.repository.BlogUserRepository;

@Service
public class BlogUserService {

    private final BlogUserRepository userRepository;
    private final BlogUserDtoConverter userDtoConverter;

    public BlogUserService(BlogUserRepository userRepository, BlogUserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public BlogUserDto createUser(CreateBlogUserRequest createUserRequest) {
        BlogUser user = new BlogUser();
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(createUserRequest.getPassword());
        userRepository.save(user);
        return userDtoConverter.convertToDto(user);
    }

    public Set<BlogUserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userDtoConverter::convertToDto).collect(Collectors.toSet());
    }

    public BlogUserDto getUserById(@NonNull String id) {
        return userDtoConverter.convertToDto(userRepository.findById(id).orElseThrow(
            () -> new BlogUserNotFoundException("User not found with id: " + id)
        ));
    }

    protected BlogUser findBlogUserById(@NonNull String id) {
        return userRepository.findById(id).orElseThrow(
            () -> new BlogUserNotFoundException("User not found with id: " + id)
        );
    }

    public void deleteUserById(@NonNull String id) {
        userDtoConverter.convertToDto(userRepository.findById(id).orElseThrow(
            () -> new BlogUserNotFoundException("User not found with id: " + id)
        ));
        userRepository.deleteById(id);
    }

    public BlogUserDto patchUserById(@NonNull String id, PatchBlogUserRequest patchBlogUserRequest) {
        BlogUser user = userRepository.findById(id).orElseThrow(
            () -> new BlogUserNotFoundException("User not found with id: " + id)
        );
        if(patchBlogUserRequest.getUsername() != null) {
            user.setUsername(patchBlogUserRequest.getUsername());
        }
        if(patchBlogUserRequest.getPassword() != null) {
            user.setPassword(patchBlogUserRequest.getPassword());
        }
        userRepository.save(user);
        return userDtoConverter.convertToDto(user);
    }

    public BlogUserDto updateUserById(@NonNull String id, UpdateBlogUserRequest updateBlogUserRequest) {
        BlogUser user = userRepository.findById(id).orElseThrow(
            () -> new BlogUserNotFoundException("User not found with id: " + id)
        );
        user.setUsername(updateBlogUserRequest.getUsername());
        user.setPassword(updateBlogUserRequest.getPassword());
        userRepository.save(user);
        return userDtoConverter.convertToDto(user);
    }

}
