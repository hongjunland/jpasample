package com.example.post.adapter.out.persistence;

import com.example.common.annotation.Mapper;
import com.example.post.domain.Post;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class PostMapper {
//    private final CommentMapper commentMapper;
    public PostJpaEntity domainToEntity(Post post){
        return PostJpaEntity.builder()
                .title(post.getTitle())
                .content(post.getContent())
//                .comments(post.getComments().stream().map())
                .build();
    }
}
