package com.example.service;

import com.example.dto.CommentReadResponse;
import com.example.dto.PostCreateRequest;
import com.example.dto.PostItemReadResponse;
import com.example.dto.PostReadResponse;
import com.example.entity.PostJpaEntity;
import com.example.repository.PostJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    public PostJpaEntity findByIdPostJpaEntity(Long id){
        return postJpaRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("해당 post가 없습니다."));
    }
    public PostReadResponse findByPostId(Long id){
        PostJpaEntity postJpaEntity = findByIdPostJpaEntity(id);
//        System.out.println(postJpaEntity.getComments().size());
        return PostReadResponse.builder()
                .postId(postJpaEntity.getPostId())
                .title(postJpaEntity.getTitle())
                .content(postJpaEntity.getContent())
                .comment(postJpaEntity.getComments().stream().map(
                            (entity) ->
                                CommentReadResponse.builder()
                            .commentId(entity.getCommentId())
                            .content(entity.getContent())
                            .postId(postJpaEntity.getPostId())
                            .build()).collect(Collectors.toList()))
                .build();
    }
    public List<PostReadResponse> findPostList(){
        List<PostJpaEntity> entityList = postJpaRepository.findAll();
        List<PostReadResponse> response = new ArrayList<>();
        for(PostJpaEntity entity: entityList){
            PostReadResponse postReadResponse = findByPostId(entity.getPostId());
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
