package com.example.jpasample.post.application.service;

import com.example.jpasample.post.adapter.in.web.response.CommentResponse;
import com.example.jpasample.post.adapter.in.web.response.CommentWithRepliesResponse;
import com.example.jpasample.post.application.port.in.CommentLoadUseCase;
import com.example.jpasample.post.application.port.in.command.CommentQuery;
import com.example.jpasample.post.application.port.out.LoadCommentPort;
import com.example.jpasample.post.domain.Comment;
import com.example.jpasample.post.domain.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoadCommentServiceTest{
    @InjectMocks
    private LoadCommentService loadCommentService;
    @Mock
    private LoadCommentPort loadCommentPort;
    @Test
    public void getCommentListByParentIdTest() {
        final Long commentId = 1L;
        final Long postId = 1L;
        CommentQuery commentQuery = CommentQuery.builder()
                .commentId(commentId)
                .build();
        Comment comment = Comment.builder()
                .id(new Comment.CommentId(commentId))
                .postId(new Post.PostId(postId))
                .parentId(new Comment.CommentId(null))
                .replies(List.of(
                        Comment.builder().id(new Comment.CommentId(11L)).postId(new Post.PostId(postId)).parentId(new Comment.CommentId(commentId)).build(),
                        Comment.builder().id(new Comment.CommentId(12L)).postId(new Post.PostId(postId)).parentId(new Comment.CommentId(commentId)).build(),
                        Comment.builder().id(new Comment.CommentId(13L)).postId(new Post.PostId(postId)).parentId(new Comment.CommentId(commentId)).build()
                ))
                .build();
        when(loadCommentPort.loadById(commentId)).thenReturn(comment);
        // When
        CommentWithRepliesResponse response = loadCommentService.getCommentListByParentId(commentQuery);
        // Then
        verify(loadCommentPort, times(1)).loadById(commentQuery.commentId());
        Assertions.assertEquals(response.commentId(), comment.getId().value());
        Assertions.assertEquals(response.postId(), comment.getPostId().value());
        Assertions.assertEquals(response.replies().size(), comment.getReplies().size());
    }
}
