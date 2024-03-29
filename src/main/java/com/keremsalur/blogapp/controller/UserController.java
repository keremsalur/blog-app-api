package com.keremsalur.blogapp.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.keremsalur.blogapp.dto.BlogUserDto;
import com.keremsalur.blogapp.dto.request.CreateBlogUserRequest;
import com.keremsalur.blogapp.dto.request.UpdateBlogUserRequest;
import com.keremsalur.blogapp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity<BlogUserDto>createUser(
        @Valid 
        @RequestBody 
        CreateBlogUserRequest createUserRequest){
        return ResponseEntity.ok(userService.createUser(createUserRequest));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<Set<BlogUserDto>>getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<BlogUserDto>getUserById(
        @NonNull
        @Valid
        @PathVariable String id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteUserById(@NonNull @PathVariable String id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ResponseEntity<BlogUserDto>updateUserById(
        @NonNull @Valid @PathVariable String id,
        @Valid @RequestBody UpdateBlogUserRequest updateBlogUserRequest
    ){
        return ResponseEntity.ok(userService.updateUserById(id,updateBlogUserRequest));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<BlogUserDto>updateUserById(
        @NonNull @Valid @PathVariable String id,
        @Valid @RequestBody CreateBlogUserRequest createBlogUserRequest
    ){
        return ResponseEntity.ok(userService.updateUserById(id,createBlogUserRequest));
    }
}
