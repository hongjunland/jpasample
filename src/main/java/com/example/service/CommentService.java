package com.example.service;

import com.example.dto.CommentCreateRequest;
import com.example.dto.CommentReadResponse;
import com.example.entity.CommentJpaEntity;
import com.example.entity.PostJpaEntity;
import com.example.repository.CommentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentService {
    private final CommentJpaRepository commentJpaRepository;
    private final PostService postService;
    @Transactional
    public boolean createComment(Long postId, CommentCreateRequest commentCreateRequest){
        PostJpaEntity postJpaEntity = postService.findByIdPostJpaEntity(postId);
        CommentJpaEntity commentJpaEntity = CommentJpaEntity.builder()
                .post(postJpaEntity)
                .content(commentCreateRequest.content())
                .build();
        postJpaEntity.createComment(commentJpaEntity);
        return true;
    }
    public List<CommentReadResponse> getCommentList(Long postId){
        List<CommentReadResponse> response = new ArrayList<>();
        PostJpaEntity postJpaEntity = postService.findByIdPostJpaEntity(postId);
//        List<CommentJpaEntity> entityList = commentJpaRepository.findByPost(postJpaEntity);
        List<CommentJpaEntity> entityList = postJpaEntity.getComments();
        for(CommentJpaEntity entity: entityList){
            CommentReadResponse item = CommentReadResponse.builder()
                    .commentId(entity.getCommentId())
                    .content(entity.getContent())
                    .postId(postId)
                    .build();
            response.add(item);
        }
        return response;
    }
}
