package com.example.post.adapter.out.persistence;

import com.example.common.annotation.PersistenceAdapter;
import com.example.post.application.port.out.CreatePostPort;
import com.example.post.domain.Post;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class PostPersistenceAdapter implements CreatePostPort {
    private final SpringDataPostRepository springDataPostRepository;
    private final PostMapper postMapper;
    @Override
    public boolean createPost(Post post) {
        PostJpaEntity postJpaEntity = postMapper.domainToEntity(post);
        springDataPostRepository.save(postJpaEntity);
        return true;
    }
}
