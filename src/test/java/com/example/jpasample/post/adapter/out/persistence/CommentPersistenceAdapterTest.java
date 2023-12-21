package com.example.jpasample.post.adapter.out.persistence;

import com.example.jpasample.post.domain.Comment;
import com.example.jpasample.post.domain.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentPersistenceAdapterTest{
    @InjectMocks
    private CommentPersistenceAdapter commentPersistenceAdapter;
    @Mock
    private SpringDataCommentRepository springDataCommentRepository;
    @Mock
    private SpringDataPostRepository springDataPostRepository;
    @Mock
    private CommentMapper commentMapper;
    @Test
    public void createCommentTest() {
        Comment.CommentId commentId = new Comment.CommentId(1L);
        String content = "content1";
        Post.PostId postId = new Post.PostId(1L);
        Comment comment = Comment.builder()
                .id(commentId)
                .content(content)
                .postId(postId)
                .build();
        CommentJpaEntity commentJpaEntity = CommentJpaEntity.builder().build();
        PostJpaEntity postJpaEntity = PostJpaEntity.builder()
                .build();
        // Given
        when(springDataPostRepository.findById(commentId.value())).thenReturn(Optional.ofNullable(postJpaEntity));
        when(commentMapper.domainToEntity(comment, postJpaEntity)).thenReturn(commentJpaEntity);
        when(springDataPostRepository.save(postJpaEntity)).thenReturn(any());
        // When
        boolean result = commentPersistenceAdapter.createComment(comment);
        // Then
        verify(springDataPostRepository, times(1)).findById(commentId.value());
        Assertions.assertTrue(result);
    }

    @Test
    public void createCommentReplyTest() {
        Comment.CommentId commentId = new Comment.CommentId(1L);
        String content = "content1";
        Post.PostId postId = new Post.PostId(1L);
        Comment.CommentId parentId = new Comment.CommentId(11L);
        Comment comment = Comment.builder()
                .id(commentId)
                .content(content)
                .postId(postId)
                .parentId(parentId)
                .build();
        PostJpaEntity postJpaEntity = PostJpaEntity.builder()
                .build();
        CommentJpaEntity parentCommentJpaEntity = CommentJpaEntity.builder().build();
        CommentJpaEntity commentJpaEntity = CommentJpaEntity.builder().build();
        when(springDataPostRepository.findById(commentId.value())).thenReturn(Optional.ofNullable(postJpaEntity));
        when(springDataCommentRepository.findById(parentId.value())).thenReturn(Optional.ofNullable(parentCommentJpaEntity));
        when(commentMapper.domainToEntityWithParent(comment, parentCommentJpaEntity, postJpaEntity)).thenReturn(commentJpaEntity);
        when(springDataCommentRepository.save(parentCommentJpaEntity)).thenReturn(any());

        // When
        boolean result = commentPersistenceAdapter.createCommentReply(comment);

        // Then
        verify(springDataPostRepository, times(1)).findById(commentId.value());
        verify(springDataCommentRepository, times(1)).findById(parentId.value());
        verify(commentMapper, times(1)).domainToEntityWithParent(comment, parentCommentJpaEntity, postJpaEntity);
        verify(springDataCommentRepository, times(1)).save(parentCommentJpaEntity);
        Assertions.assertTrue(result);
    }
}
