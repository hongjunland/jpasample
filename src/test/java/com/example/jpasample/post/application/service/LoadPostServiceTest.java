package com.example.jpasample.post.application.service;


import com.example.jpasample.post.adapter.in.web.response.CommentResponse;
import com.example.jpasample.post.adapter.in.web.response.PostResponse;
import com.example.jpasample.post.adapter.in.web.response.PostSearchResponse;
import com.example.jpasample.post.application.port.in.command.PostQuery;
import com.example.jpasample.post.application.port.in.command.PostSearchByTitleQuery;
import com.example.jpasample.post.application.port.out.LoadPostPort;
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
class LoadPostServiceTest{
    @InjectMocks
    private LoadPostService loadPostService;
    @Mock
    private LoadPostPort loadPostPort;

    @Test
    public void getPostByIdTest() {
        // Given
        final Long postId = 1L;
        final String title = "title1";
        final String content = "content11";
        final List<Comment> comments = List.of(
                Comment.builder().id(new Comment.CommentId(1L)).postId(new Post.PostId(postId)).build(),
                Comment.builder().id(new Comment.CommentId(2L)).postId(new Post.PostId(postId)).build(),
                Comment.builder().id(new Comment.CommentId(3L)).postId(new Post.PostId(postId)).build());
        PostQuery postQuery = PostQuery.builder()
                .postId(postId)
                .build();
        Post post = Post.builder()
                .id(new Post.PostId(postId))
                .title(title)
                .content(content)
                .comments(comments)
                .build();
        when(loadPostPort.loadById(postId)).thenReturn(post);
        // When
        PostResponse postResponse = loadPostService.getPostById(postQuery);
        // Then
        verify(loadPostPort, times(1)).loadById(postId);
        Assertions.assertEquals(postId, postResponse.postId());
        Assertions.assertEquals(title, postResponse.title());
        Assertions.assertEquals(content, postResponse.content());
        Assertions.assertEquals(post.getComments().size(), postResponse.comment().size());
    }

    @Test
    public void searchPostListByTitleTest() {
        // Given
        String title = "ti";
        PostSearchByTitleQuery postSearchByTitleQuery = PostSearchByTitleQuery.builder()
                .title(title)
                .build();
        List<Post> postList = List.of(
                Post.builder().id(new Post.PostId(1L)).title("title1").build(),
                Post.builder().id(new Post.PostId(2L)).title("title12").build(),
                Post.builder().id(new Post.PostId(3L)).title("title13").build()
        );
        when(loadPostPort.searchByTitle(postSearchByTitleQuery.title())).thenReturn(postList);
        // When
        List<PostSearchResponse> response = loadPostService.searchPostListByTitle(postSearchByTitleQuery);

        // Then
        verify(loadPostPort, times(1)).searchByTitle(title);
        Assertions.assertEquals(response.size(), postList.size());
    }
}
