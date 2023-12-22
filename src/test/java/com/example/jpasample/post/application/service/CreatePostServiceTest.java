package com.example.jpasample.post.application.service;

import com.example.jpasample.post.application.port.in.command.PostCreateCommand;
import com.example.jpasample.post.application.port.out.CreatePostPort;
import com.example.jpasample.post.domain.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePostServiceTest{
    @InjectMocks
    private CreatePostService createPostService;
    @Mock
    private CreatePostPort createPostPort;

    @Test
    public void createPostTest() {
        // Given
        PostCreateCommand postCreateCommand = PostCreateCommand.builder()
                .title("title1")
                .content("content1")
                .build();
        when(createPostPort.createPost(any(Post.class))).thenReturn(true);
        // When
        boolean result = createPostService.createPost(postCreateCommand);
        // Then
        verify(createPostPort, times(1)).createPost(any(Post.class));
        Assertions.assertTrue(result);
    }
}
