package com.keremsalur.blogapp.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.keremsalur.blogapp.dto.post.PostAuthorDto;
import com.keremsalur.blogapp.dto.post.PostDto;
import com.keremsalur.blogapp.dto.request.post.CreatePostRequest;
import com.keremsalur.blogapp.service.PostService;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(
        @Valid @RequestBody CreatePostRequest createPostRequest) {
        return ResponseEntity.ok(postService.createPost(createPostRequest));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("post/{id}")
    public ResponseEntity<PostAuthorDto> getPostById(@NonNull @Valid @PathVariable String id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all-posts")
    public ResponseEntity<Set<PostAuthorDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/posts-by-author/{authorId}")
    public ResponseEntity<Set<PostDto>> getPostsByAuthorId(@NonNull @Valid @PathVariable String authorId) {
        return ResponseEntity.ok(postService.getPostsByAuthorId(authorId));
    }
}
