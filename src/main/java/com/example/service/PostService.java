package com.example.service;

import com.example.dto.PostCreateRequest;
import com.example.dto.PostReadResponse;
import com.example.entity.PostJpaEntity;
import com.example.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostJpaRepository postJpaRepository;
    public boolean createPost(PostCreateRequest postCreateRequest){
        PostJpaEntity postJpaEntity = PostJpaEntity.builder()
                .title(postCreateRequest.title())
                .content(postCreateRequest.content())
                .build();
        postJpaRepository.save(postJpaEntity);
        return true;
    }
    public PostJpaEntity findByPostId(Long id){
        return postJpaRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("해당 post가 없습니다."));
    }
    public List<PostReadResponse> findPostList(){
        List<PostJpaEntity> entityList = postJpaRepository.findAll();
        List<PostReadResponse> response = new ArrayList<>();
        for(PostJpaEntity entity: entityList){
            PostReadResponse postReadResponse = new PostReadResponse(entity.getPostId(), entity.getTitle(), entity.getContent());
            response.add(postReadResponse);
        }
        return response;
    }
    public List<PostJpaEntity> findPostJpaList(){
        return postJpaRepository.findAll();
    }

    public void deleteAll() {
        postJpaRepository.deleteAll();
    }
    public long getCount(){return postJpaRepository.count();}
}
