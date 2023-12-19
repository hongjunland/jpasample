package com.example.post.adapter.out.persistence;

import com.example.common.annotation.PersistenceAdapter;
import com.example.post.application.port.out.LoadPostPort;
import com.example.post.domain.Post;
import lombok.RequiredArgsConstructor;
@PersistenceAdapter
@RequiredArgsConstructor
class PostLoadPersistenceAdapter implements LoadPostPort {
    private final SpringDataPostRepository springDataPostRepository;
    private final PostMapper postMapper;
    @Override
    public Post loadById(Long id) {
        PostJpaEntity postJpaEntity = springDataPostRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return postMapper.entityToDomain(postJpaEntity);
    }
}
