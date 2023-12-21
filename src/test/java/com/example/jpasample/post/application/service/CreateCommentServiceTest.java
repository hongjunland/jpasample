package com.example.jpasample.post.application.service;

import com.example.jpasample.post.application.port.in.CommentCreateUseCase;
import com.example.jpasample.post.application.port.in.command.CommentCreateCommand;
import com.example.jpasample.post.application.port.in.command.CommentReplyCreateCommand;
import com.example.jpasample.post.application.port.out.CreateCommentPort;
import com.example.jpasample.post.application.port.out.CreateCommentReplyPort;
import com.example.jpasample.post.domain.Comment;
import com.example.jpasample.post.domain.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCommentServiceTest {
    @InjectMocks
    private CreateCommentService commentService;
    @Mock
    private CreateCommentPort createCommentPort;
    @Mock
    private CreateCommentReplyPort createCommentReply;
    @Test
    public void createCommentTest() {
        final String content = "content1";
        final Long postId = 1L;
        // Given
        CommentCreateCommand commentCreateCommand = CommentCreateCommand.builder()
                .content(content)
                .postId(postId)
                .build();
        when(createCommentPort.createComment(any(Comment.class))).thenReturn(true);
        // When
        boolean result = commentService.createComment(commentCreateCommand);
        // Then
        verify(createCommentPort, times(1)).createComment(any(Comment.class));
        Assertions.assertTrue(result);
    }

    @Test
    public void createCommentReply() {
        // Given
        final String content = "content1";
        final Long postId = 1L;
        final Long parentId = 1L;
        CommentReplyCreateCommand commentReplyCreateCommand = CommentReplyCreateCommand.builder()
                .content(content)
                .postId(postId)
                .parentId(parentId)
                .build();
        when(createCommentReply.createCommentReply(any(Comment.class))).thenReturn(true);
        // When
        boolean result = commentService.createCommentReply(commentReplyCreateCommand);
        // Then
        verify(createCommentReply, times(1)).createCommentReply(any(Comment.class));
        Assertions.assertTrue(result);
    }
}
