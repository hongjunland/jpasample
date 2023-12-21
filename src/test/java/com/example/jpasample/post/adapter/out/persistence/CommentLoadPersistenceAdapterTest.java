package com.example.jpasample.post.adapter.out.persistence;

import com.example.jpasample.post.application.port.out.LoadCommentPort;
import com.example.jpasample.post.domain.Comment;
import com.example.jpasample.post.domain.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentLoadPersistenceAdapterTest{
    @InjectMocks
    private CommentLoadPersistenceAdapter commentLoadPersistenceAdapter;
    @Mock
    private SpringDataCommentRepository springDataCommentRepository;
    @Mock
    private CommentMapper commentMapper;
    @Test
    public void loadByIdTest() {
        // Given
        final Long id = 1L;
        final Long postId = 1L;
        final String content = "content1";
        CommentJpaEntity commentJpaEntity = CommentJpaEntity.builder()
                .commentId(id)
                .content(content)
                .post(Mockito.mock(PostJpaEntity.class))
                .parent(null)
                .build();
        Comment expectedComment = Comment.builder()
                .id(new Comment.CommentId(id))
                .postId(new Post.PostId(postId))
                .content(content)
                .build();
        when(springDataCommentRepository.findById(id)).thenReturn(Optional.ofNullable(commentJpaEntity));
        when(commentMapper.entityToDomainWithReplies(commentJpaEntity)).thenReturn(expectedComment);

        // When
        Comment comment = commentLoadPersistenceAdapter.loadById(id);

        // Then
        verify(springDataCommentRepository, times(1)).findById(id);
        verify(commentMapper, times(1)).entityToDomainWithReplies(commentJpaEntity);
        Assertions.assertEquals(comment.getId(), expectedComment.getId());
        Assertions.assertEquals(comment.getContent(), expectedComment.getContent());
        Assertions.assertEquals(comment.getParentId(), expectedComment.getParentId());
        Assertions.assertEquals(comment.getPostId(), expectedComment.getPostId());
        Assertions.assertEquals(comment.getReplies(), expectedComment.getReplies());
    }
}
