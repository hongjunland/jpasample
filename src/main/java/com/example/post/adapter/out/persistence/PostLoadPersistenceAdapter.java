package com.example.post.adapter.out.persistence;

import com.example.common.annotation.PersistenceAdapter;
import com.example.post.application.port.out.LoadPostPort;
import com.example.post.domain.Post;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
class PostLoadPersistenceAdapter implements LoadPostPort {
    private final SpringDataPostRepository springDataPostRepository;
    private final PostMapper postMapper;
    @Override
    public Post loadById(Long id) {
        PostJpaEntity postJpaEntity = springDataPostRepository.findByIdWithComments(id)
                .orElseThrow(RuntimeException::new);
        return postMapper.entityToDomain(postJpaEntity);
    }
    @Override
    public List<Post> searchByTitle(String title){
        List<PostJpaEntity> postJpaEntityList = springDataPostRepository.searchByTitle(title);
        return postJpaEntityList.stream()
                .map(postMapper::entityToDomain)
                .collect(Collectors.toList());
    }
}
