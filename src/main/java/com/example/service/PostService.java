//package com.example.service;
//
//import com.example.dto.PostCreateRequest;
//import com.example.post.adapter.in.response.PostResponse;
//import com.example.post.adapter.out.persistence.PostJpaEntity;
//import com.example.post.adapter.out.persistence.PostMapper;
//import com.example.repository.PostJpaRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.NoSuchElementException;
//
//@Service
//@RequiredArgsConstructor
//public class PostService {
//    private final PostJpaRepository postJpaRepository;
//    private final PostMapper postMapper;
//    public void savePost(PostJpaEntity postJpaEntity){
//        postJpaRepository.save(postJpaEntity);
//    }
//    public boolean createPost(PostCreateRequest postCreateRequest){
//        PostJpaEntity postJpaEntity = PostJpaEntity.builder()
//                .title(postCreateRequest.title())
//                .content(postCreateRequest.content())
//                .build();
//        postJpaRepository.save(postJpaEntity);
//        return true;
//    }
//    public PostJpaEntity findByIdPostJpaEntity(Long id){
//        return postJpaRepository.findById(id)
//                .orElseThrow(()-> new NoSuchElementException("해당 post가 없습니다."));
//    }
//    public PostResponse findByPostId(Long id){
//        PostJpaEntity postJpaEntity = findByIdPostJpaEntity(id);
//        return postMapper.entityToResponse(postJpaEntity);
//    }
//    public List<PostResponse> findPostList(){
//        List<PostJpaEntity> entityList = postJpaRepository.findAll();
//        List<PostResponse> response = new ArrayList<>();
//        for(PostJpaEntity entity: entityList){
//            PostResponse postReadResponse = findByPostId(entity.getPostId());
//            response.add(postReadResponse);
//        }
//        return response;
//    }
//    public List<PostJpaEntity> findPostJpaList(){
//        return postJpaRepository.findAll();
//    }
//
//    public void deleteAll() {
//        postJpaRepository.deleteAll();
//    }
//}
