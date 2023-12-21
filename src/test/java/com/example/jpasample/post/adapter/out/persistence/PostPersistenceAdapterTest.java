package com.example.jpasample.post.adapter.out.persistence;

import com.example.jpasample.post.domain.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostPersistenceAdapterTest {
    @InjectMocks
    private PostPersistenceAdapter postPersistenceAdapter;
    @Mock
    private SpringDataPostRepository springDataPostRepository;
    @Mock
    private PostMapper postMapper;
    @Test
    public void createPostTest() {
        Post.PostId postId = new Post.PostId(1L);
        Post post = Post.builder().build();
        PostJpaEntity postJpaEntity = PostJpaEntity.builder()
                .postId(postId.value())
                .build();
        when(postMapper.domainToEntity(post)).thenReturn(postJpaEntity);
        when(springDataPostRepository.save(postJpaEntity)).thenReturn(postJpaEntity);

        // When
        boolean result = postPersistenceAdapter.createPost(post);
        // Then
        verify(postMapper, times(1)).domainToEntity(post);
        verify(springDataPostRepository, times(1)).save(postJpaEntity);
        Assertions.assertTrue(result);
    }
}
