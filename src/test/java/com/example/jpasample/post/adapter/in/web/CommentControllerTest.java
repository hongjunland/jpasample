package com.example.jpasample.post.adapter.in.web;

import com.example.jpasample.common.response.SuccessApiResponse;
import com.example.jpasample.post.adapter.in.web.request.CommentCreateRequest;
import com.example.jpasample.post.adapter.in.web.response.CommentResponse;
import com.example.jpasample.post.adapter.in.web.response.CommentWithRepliesResponse;
import com.example.jpasample.post.application.port.in.CommentCreateUseCase;
import com.example.jpasample.post.application.port.in.CommentLoadUseCase;
import com.example.jpasample.post.application.port.in.command.CommentCreateCommand;
import com.example.jpasample.post.application.port.in.command.CommentQuery;
import com.example.jpasample.post.application.port.in.command.CommentReplyCreateCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {
    @InjectMocks
    private CommentController commentController;
    @Mock
    private CommentCreateUseCase createCommentUseCase;
    @Mock
    private CommentLoadUseCase commentLoadUseCase;

    @Test
    public void createCommentTest(){
        // Given
        final Long postId = 1L;
        final String content = "content1";
        CommentCreateRequest commentCreateRequest = CommentCreateRequest.builder()
                .content(content)
                .build();
        CommentCreateCommand commentCreateCommand = CommentCreateCommand.builder()
                .content(commentCreateRequest.content())
                .postId(postId)
                .build();
        when(createCommentUseCase.createComment(commentCreateCommand)).thenReturn(true);
        // When
        SuccessApiResponse response = commentController.createComment(commentCreateRequest, postId);

        // Then
        verify(createCommentUseCase, times(1)).createComment(commentCreateCommand);
        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
    @Test
    public void createReplyCommentTest(){
        // Given
        final Long postId = 1L;
        final Long commentId = 1L;
        final String content = "content1";
        CommentCreateRequest commentCreateRequest = CommentCreateRequest.builder()
                .content(content)
                .build();
        CommentReplyCreateCommand commentCreateCommand = CommentReplyCreateCommand.builder()
                .content(commentCreateRequest.content())
                .parentId(commentId)
                .postId(postId)
                .build();
        when(createCommentUseCase.createCommentReply(commentCreateCommand)).thenReturn(true);

        // When
        SuccessApiResponse response = commentController.createReplyComment(commentCreateRequest, postId, commentId);

        // Then
        verify(createCommentUseCase, times(1)).createCommentReply(commentCreateCommand);
        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
    @Test
    public void getCommentByCommentIdTest(){
        // Given
        final Long postId = 1L;
        final Long commentId = 1L;
        CommentQuery getCommentQuery = CommentQuery.builder()
                .commentId(commentId)
                .postId(postId)
                .build();
        final String content = "content1";
        CommentWithRepliesResponse expectedResponse = CommentWithRepliesResponse.builder()
                .commentId(commentId)
                .content(content)
                .parentId(null)
                .replies(List.of(
                        CommentResponse.builder().commentId(11L).parentId(commentId).build(),
                        CommentResponse.builder().commentId(12L).parentId(commentId).build(),
                        CommentResponse.builder().commentId(13L).parentId(commentId).build()))
                .build();
        when(commentLoadUseCase.getCommentListByParentId(getCommentQuery)).thenReturn(expectedResponse);

        // When
        SuccessApiResponse<CommentWithRepliesResponse> response = (SuccessApiResponse<CommentWithRepliesResponse>) commentController.getCommentByCommentId(postId, commentId);

        // Then
        verify(commentLoadUseCase, times(1)).getCommentListByParentId(getCommentQuery);
        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
        Assertions.assertEquals(response.getData().commentId(), expectedResponse.commentId());
        Assertions.assertEquals(response.getData().replies().size(), expectedResponse.replies().size());
    }
}
