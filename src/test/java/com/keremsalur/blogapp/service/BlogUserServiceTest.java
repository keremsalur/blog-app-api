package com.keremsalur.blogapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.keremsalur.blogapp.dto.bloguser.BlogUserDto;
import com.keremsalur.blogapp.dto.converter.bloguser.BlogUserDtoConverter;
import com.keremsalur.blogapp.dto.request.bloguser.CreateBlogUserRequest;
import com.keremsalur.blogapp.dto.request.bloguser.PatchBlogUserRequest;
import com.keremsalur.blogapp.dto.request.bloguser.UpdateBlogUserRequest;
import com.keremsalur.blogapp.exception.BlogUserNotFoundException;
import com.keremsalur.blogapp.model.BlogUser;
import com.keremsalur.blogapp.repository.BlogUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Optional;

public class BlogUserServiceTest {
    private BlogUserService blogUserService;
    private BlogUserDtoConverter blogUserDtoConverter;
    private BlogUserRepository blogUserRepository;

    @BeforeEach
    public void setup() {
        blogUserRepository = Mockito.mock(BlogUserRepository.class);
        blogUserDtoConverter = Mockito.mock(BlogUserDtoConverter.class);
        blogUserService = new BlogUserService(blogUserRepository, blogUserDtoConverter);
    }

    @Test
    public void test_createUser_withValidUsernameAndPassword_saveToRepository() {
        // given
        CreateBlogUserRequest testRequest = new CreateBlogUserRequest();
        testRequest.setUsername("username");
        testRequest.setPassword("password");

        BlogUserDto testUserDto = new BlogUserDto("id", "username", "password");

        // when
        Mockito.when(blogUserRepository.save(any(BlogUser.class))).thenReturn(new BlogUser());
        Mockito.when(blogUserDtoConverter.convertToDto(any(BlogUser.class))).thenReturn(testUserDto);
        BlogUserDto result = blogUserService.createUser(testRequest);

        // then
        assertEquals("username", result.getUsername());
        assertEquals("password", result.getPassword());
        Mockito.verify(blogUserRepository).save(any(BlogUser.class));
    }

    @Test
    public void test_getAllBlogUsers_returnsBlogUserDtoList() {
        // given
        List<BlogUser> testBlogUsers = new ArrayList<>();
        testBlogUsers.add(new BlogUser("id1", "username1", "password1"));
        testBlogUsers.add(new BlogUser("id2", "username2", "password2"));
        int expetedSize = 2;

        // when
        Mockito.when(blogUserRepository.findAll()).thenReturn(testBlogUsers);
        Mockito.when(blogUserDtoConverter.convertToDto(any(BlogUser.class)))
            .thenReturn(new BlogUserDto("id1", "username1", "password1"))
            .thenReturn(new BlogUserDto("id2", "username2", "password2"));
        
        Set<BlogUserDto> result = blogUserService.getAllUsers();

        // then
        assertEquals(expetedSize, result.size());
        assertTrue(result.stream().anyMatch(dto -> dto.getUsername().equals("username1")));
        assertTrue(result.stream().anyMatch(dto -> dto.getUsername().equals("username2")));
    }

    @Test
    public void test_getUserById_returnsBlogUser() {
        // given
        BlogUser testUser = new BlogUser("id", "username", "password");
        BlogUserDto testUserDto = new BlogUserDto("id", "username", "password");

        // when
        Mockito.when(blogUserRepository.findById("id")).thenReturn(Optional.of(testUser));
        Mockito.when(blogUserDtoConverter.convertToDto(any(BlogUser.class))).thenReturn(testUserDto);
        BlogUserDto result = blogUserService.getUserById("id");

        // then
        assertEquals(testUserDto, result);
    }

    @Test
    public void test_getUserById_blogUserIdDoesNotExist_throwsBlogUserNotFoundException() {
        // when
        Mockito.when(blogUserRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(BlogUserNotFoundException.class, () -> blogUserService.getUserById("id"));
        
        // then
        Mockito.verifyNoInteractions(blogUserDtoConverter);
    }

    @Test
    public void test_deleteUserById_blogUserIsDeleted() {
        // given
        BlogUser testUser = new BlogUser("id", "username", "password");
        
        // when
        Mockito.when(blogUserRepository.findById("id")).thenReturn(Optional.of(testUser));
        blogUserService.deleteUserById("id");

        // then
        Mockito.verify(blogUserRepository).deleteById("id");
    }

    @Test
    public void test_deleteUserById_blogUserIdDoesNotExist_throwsBlogUserNotFoundException() {
        
        // when
        Mockito.when(blogUserRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(BlogUserNotFoundException.class, () -> blogUserService.deleteUserById("id"));
        
        // then
        Mockito.verifyNoInteractions(blogUserDtoConverter);
    }

    @Test
    public void test_updateUserById_blogUserIsUpdated() {
        // given
        BlogUser testBlogUser = new BlogUser("id", "username", "password");
        BlogUser updatedTestBlogUser = new BlogUser("id", "usernameUpdate", "passwordUpdate");
        BlogUserDto updatedBlogUserDto = new BlogUserDto("id", "usernameUpdate", "passwordUpdate");

        UpdateBlogUserRequest testRequest = new UpdateBlogUserRequest();
        testRequest.setUsername("usernameUpdate");
        testRequest.setPassword("passwordUpdate");

        // when
        Mockito.when(blogUserRepository.findById("id")).thenReturn(Optional.of(testBlogUser));
        Mockito.when(blogUserRepository.save(any(BlogUser.class))).thenReturn(updatedTestBlogUser);
        Mockito.when(blogUserDtoConverter.convertToDto(updatedTestBlogUser)).thenReturn(updatedBlogUserDto);

        BlogUserDto result = blogUserService.updateUserById("id", testRequest);

        assertEquals(updatedBlogUserDto, result);

    }

    @Test
    public void test_updateUserById_blogUserIdDoesNotExist_throwsBlogUserNotFoundException() {

        // when
        Mockito.when(blogUserRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(BlogUserNotFoundException.class, () -> blogUserService.updateUserById("id", new UpdateBlogUserRequest()));
        
        // then
        Mockito.verifyNoInteractions(blogUserDtoConverter);
    }

    @Test
    public void test_pacthUserById_blogUserIsPatched() {
        // given
        BlogUser testBlogUser = new BlogUser("id", "username", "password");
        BlogUser patchedTestBlogUser = new BlogUser("id", "username", "passwordPatch");
        BlogUserDto patchedBlogUserDto = new BlogUserDto("id", "username", "passwordPatch");
        
        PatchBlogUserRequest testRequest = new PatchBlogUserRequest();
        testRequest.setPassword("passwordPatch");

        // when
        Mockito.when(blogUserRepository.findById("id")).thenReturn(Optional.of(testBlogUser));
        Mockito.when(blogUserRepository.save(any(BlogUser.class))).thenReturn(patchedTestBlogUser);
        Mockito.when(blogUserDtoConverter.convertToDto(patchedTestBlogUser)).thenReturn(patchedBlogUserDto);

        BlogUserDto result = blogUserService.patchUserById("id", testRequest);

        assertEquals(patchedBlogUserDto, result);
    }

    @Test
    public void test_patchUserById_blogUserIdDoesNotExist_throwsBlogUserNotFoundException(){

        // when
        Mockito.when(blogUserRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(BlogUserNotFoundException.class, () -> blogUserService.patchUserById("id", new PatchBlogUserRequest()));
        
        // then
        Mockito.verifyNoInteractions(blogUserDtoConverter);
    }

}
