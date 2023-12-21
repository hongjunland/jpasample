package com.example.jpasample.post.adapter.out.persistence;

import com.example.jpasample.post.adapter.in.web.response.PostResponse;
import com.example.jpasample.post.application.port.out.LoadPostPort;
import com.example.jpasample.post.domain.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostLoadPersistenceAdapterTest{
    @InjectMocks
    private PostLoadPersistenceAdapter postLoadPersistenceAdapter;
    @Mock
    private SpringDataPostRepository springDataPostRepository;
    @Mock
    private PostMapper postMapper;
    @Test
    public void loadByIdTest() {
        // Given
        final Long postId = 1L;
        PostJpaEntity postJpaEntity = PostJpaEntity.builder()
                .postId(postId)
                .build();
        Post expectedPost = Post.builder()
                .id(new Post.PostId(postId))
                .build();
        when(springDataPostRepository.findByIdWithComments(postId)).thenReturn(Optional.ofNullable(postJpaEntity));
        when(postMapper.entityToDomain(postJpaEntity)).thenReturn(expectedPost);

        // When
        Post post = postLoadPersistenceAdapter.loadById(postId);

        // Then
        verify(springDataPostRepository, times(1)).findByIdWithComments(postId);
        verify(postMapper, times(1)).entityToDomain(postJpaEntity);
        Assertions.assertEquals(post.getId(), expectedPost.getId());
        Assertions.assertEquals(post, expectedPost);
    }
    @Test
    public void searchByTitleTest(){
        final String title = "ti";
        List<PostJpaEntity> postJpaEntityList = List.of(
                PostJpaEntity.builder().postId(1L).title("title1").content("content1").build(),
                PostJpaEntity.builder().postId(2L).title("title2").content("content2").build(),
                PostJpaEntity.builder().postId(3L).title("title3").content("content3").build()
        );
        List<Post> expectedPostList = List.of(
                Post.builder().id(new Post.PostId(1L)).title("title1").content("content1").build(),
                Post.builder().id(new Post.PostId(2L)).title("title2").content("content2").build(),
                Post.builder().id(new Post.PostId(3L)).title("title3").content("content3").build()
        );
        when(springDataPostRepository.searchByTitle(title)).thenReturn(postJpaEntityList);
        // When
        List<Post> postList = postLoadPersistenceAdapter.searchByTitle(title);
        // Then
        verify(springDataPostRepository, times(1)).searchByTitle(title);
        Assertions.assertEquals(postList.size(), expectedPostList.size());
    }
}
