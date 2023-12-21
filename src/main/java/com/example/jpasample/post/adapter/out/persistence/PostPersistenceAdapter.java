package com.example.jpasample.post.adapter.out.persistence;

import com.example.jpasample.common.annotation.PersistenceAdapter;
import com.example.jpasample.post.application.port.out.CreatePostPort;
import com.example.jpasample.post.domain.Post;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class PostPersistenceAdapter implements CreatePostPort {
    private final SpringDataPostRepository springDataPostRepository;
    private final PostMapper postMapper;
    @Override
    public boolean createPost(Post post) {
        PostJpaEntity postJpaEntity = postMapper.domainToEntity(post);
        springDataPostRepository.save(postJpaEntity);
        return true;
    }
}
