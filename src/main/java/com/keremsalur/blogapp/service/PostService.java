package com.keremsalur.blogapp.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.keremsalur.blogapp.dto.converter.post.PostAuthorDtoConverter;
import com.keremsalur.blogapp.dto.converter.post.PostDtoConverter;
import com.keremsalur.blogapp.dto.post.PostAuthorDto;
import com.keremsalur.blogapp.dto.post.PostDto;
import com.keremsalur.blogapp.dto.request.post.CreatePostRequest;
import com.keremsalur.blogapp.exception.PostNotFoundException;
import com.keremsalur.blogapp.model.BlogUser;
import com.keremsalur.blogapp.model.Post;
import com.keremsalur.blogapp.repository.PostRepository;

import io.micrometer.common.lang.NonNull;

@Service
public class PostService {
    private final BlogUserService blogUserService;
    private final PostRepository postRepository;
    private final PostDtoConverter postDtoConverter;
    private final PostAuthorDtoConverter postAuthorDtoConverter;

    public PostService(BlogUserService blogUserService, PostRepository postRepository, PostDtoConverter postDtoConverter, PostAuthorDtoConverter postAuthorDtoConverter) {
        this.blogUserService = blogUserService;
        this.postRepository = postRepository;
        this.postDtoConverter = postDtoConverter;
        this.postAuthorDtoConverter = postAuthorDtoConverter;
    }

    public PostDto createPost(CreatePostRequest createPostRequest) {
        BlogUser blogUser = blogUserService.findBlogUserById(createPostRequest.getAuthorId());
        Post post = new Post();
        post.setTitle(createPostRequest.getTitle());
        post.setContent(createPostRequest.getContent());
        post.setAuthor(blogUser);
        post = postRepository.save(post);
        return postDtoConverter.convertToDto(post);
    }

    public PostAuthorDto getPostById(@NonNull String id){
        return postAuthorDtoConverter.convertToDto(postRepository.findById(id).orElseThrow(
            () -> new PostNotFoundException("Post not found with id: " + id)
        ));
    }

    public Set<PostAuthorDto> getAllPosts() {
        return postRepository.findAll().stream().map(postAuthorDtoConverter::convertToDto).collect(Collectors.toSet());
    }


    // Boş dönüyor.
    public Set<PostDto> getPostsByAuthorId(@NonNull String authorId) {
        BlogUser blogUser =  blogUserService.findBlogUserById(authorId);
        return blogUser.getPosts().stream().map(postDtoConverter::convertToDto).collect(Collectors.toSet());
    }

}
