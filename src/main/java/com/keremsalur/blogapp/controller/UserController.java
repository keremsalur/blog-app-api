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

import com.keremsalur.blogapp.dto.bloguser.BlogUserDto;
import com.keremsalur.blogapp.dto.request.bloguser.CreateBlogUserRequest;
import com.keremsalur.blogapp.dto.request.bloguser.PatchBlogUserRequest;
import com.keremsalur.blogapp.dto.request.bloguser.UpdateBlogUserRequest;
import com.keremsalur.blogapp.service.BlogUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/blog-users")
public class UserController {
    private final BlogUserService userService;

    public UserController(BlogUserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<BlogUserDto>createUser(
        @Valid @RequestBody CreateBlogUserRequest createUserRequest){
        return ResponseEntity.ok(userService.createUser(createUserRequest));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all-users")
    public ResponseEntity<Set<BlogUserDto>>getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{id}")
    public ResponseEntity<BlogUserDto>getUserById(
        @NonNull @Valid @PathVariable String id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void>deleteUserById(@NonNull @PathVariable String id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/user/{id}")
    public ResponseEntity<BlogUserDto>patchUserById (
        @NonNull @Valid @PathVariable String id,
        @Valid @RequestBody PatchBlogUserRequest patchBlogUserRequest
    ){
        return ResponseEntity.ok(userService.patchUserById(id,patchBlogUserRequest));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user/{id}")
    public ResponseEntity<BlogUserDto>updateUserById(
        @NonNull @Valid @PathVariable String id,
        @Valid @RequestBody UpdateBlogUserRequest updateBlogUserRequest
    ){
        return ResponseEntity.ok(userService.updateUserById(id,updateBlogUserRequest));
    }
}
