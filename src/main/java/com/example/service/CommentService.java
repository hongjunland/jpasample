//package com.example.service;
//
//import com.example.comment.adapter.in.request.CommentCreateRequest;
//import com.example.comment.adapter.in.response.CommentResponse;
//import com.example.comment.adapter.out.persistence.CommentJpaEntity;
//import com.example.post.adapter.out.persistence.PostJpaEntity;
//import com.example.repository.CommentJpaRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class CommentService {
//    private final CommentJpaRepository commentJpaRepository;
//    private final PostService postService;
//    private final CommentMapper commentMapper;
//    @Transactional
//    public boolean createComment(Long postId, CommentCreateRequest commentCreateRequest){
//        PostJpaEntity postJpaEntity = postService.findByIdPostJpaEntity(postId);
//        CommentJpaEntity commentJpaEntity = CommentJpaEntity.builder()
//                .post(postJpaEntity)
//                .content(commentCreateRequest.content())
//                .build();
//        postJpaEntity.createComment(commentJpaEntity);
//        postService.savePost(postJpaEntity);
//        return true;
//    }
//    public List<CommentResponse> getCommentList(Long postId){
//        List<CommentResponse> response = new ArrayList<>();
//        PostJpaEntity postJpaEntity = postService.findByIdPostJpaEntity(postId);
//        List<CommentJpaEntity> entityList = postJpaEntity.getComments();
//        for(CommentJpaEntity entity: entityList){
//            response.add(commentMapper.entityToResponse(entity));
//        }
//        return response;
//    }
//}
