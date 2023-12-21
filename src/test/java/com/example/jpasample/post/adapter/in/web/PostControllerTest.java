package com.example.jpasample.post.adapter.in.web;

import com.example.jpasample.common.response.SuccessApiResponse;
import com.example.jpasample.post.adapter.in.web.request.PostCreateRequest;
import com.example.jpasample.post.adapter.in.web.response.CommentResponse;
import com.example.jpasample.post.adapter.in.web.response.PostResponse;
import com.example.jpasample.post.adapter.in.web.response.PostSearchResponse;
import com.example.jpasample.post.application.port.in.PostCreateUseCase;
import com.example.jpasample.post.application.port.in.PostLoadUseCase;
import com.example.jpasample.post.application.port.in.command.PostCreateCommand;
import com.example.jpasample.post.application.port.in.command.PostQuery;
import com.example.jpasample.post.application.port.in.command.PostSearchByTitleQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {
    @InjectMocks
    private PostController postController;
    @Mock
    private PostCreateUseCase createPostUseCase;
    @Mock
    private PostLoadUseCase getPostUseCase;

    @DisplayName("게시물 생성기능")
    @Test
    public void createPostTest(){
        // Given
        PostCreateRequest createRequest = PostCreateRequest.builder()
                .title("title1")
                .content("content1")
                .build();
        when(createPostUseCase.createPost(any(PostCreateCommand.class))).thenReturn(true);
        // When
        SuccessApiResponse response = postController.createPost(createRequest);
        // Then
        verify(createPostUseCase, times(1)).createPost(any(PostCreateCommand.class));
        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
    @DisplayName("게시물 아이디별 단일조회")
    @Test
    public void getPostByIdTest(){
        // Given
        final Long postId = 1L;
        final String title = "title1";
        final String content = "content1";
        final List<CommentResponse> comments = List.of(
                CommentResponse.builder().commentId(1L).build(),
                CommentResponse.builder().commentId(2L).build(),
                CommentResponse.builder().commentId(3L).build());
        PostQuery postQuery = PostQuery.builder()
                .postId(postId)
                .build();
        PostResponse postResponse = PostResponse.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .comment(comments)
                .build();
        when(getPostUseCase.getPostById(postQuery)).thenReturn(postResponse);
        // When
        SuccessApiResponse<PostResponse> response = (SuccessApiResponse<PostResponse>) postController.getPostById(postId);

        //        // Then
        verify(getPostUseCase, times(1)).getPostById(postQuery);
        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
        Assertions.assertEquals(response.getData().postId(), postId);
        Assertions.assertEquals(response.getData().title(), title);
        Assertions.assertEquals(response.getData().content(), content);
        Assertions.assertEquals(response.getData().comment().size(), comments.size());
    }
    @DisplayName("게시물 제목 검색")
    @Test
    public void getPostListByTitle(){
        // Given
        final String keyword = "title";
        PostSearchByTitleQuery postSearchByTitleQuery = PostSearchByTitleQuery.builder()
                .title(keyword)
                .build();
        List<PostSearchResponse> expectedResponse = List.of(
                PostSearchResponse.builder().postId(1L).title("title1").build(),
                PostSearchResponse.builder().postId(2L).title("2title2").build(),
                PostSearchResponse.builder().postId(3L).title("title3").build()
        );
        when(getPostUseCase.searchPostListByTitle(postSearchByTitleQuery)).thenReturn(expectedResponse);

        // When
        SuccessApiResponse<List<PostSearchResponse>> response = (SuccessApiResponse<List<PostSearchResponse>>) postController.getPostListByTitle(keyword);

        // Then
        verify(getPostUseCase, times(1)).searchPostListByTitle(postSearchByTitleQuery);
        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
        Assertions.assertEquals(response.getData().size(), expectedResponse.size());
    }
}
