package com.example.post.adapter.out.persistence;

import com.example.common.annotation.Mapper;
import com.example.post.domain.Post;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@Mapper
@RequiredArgsConstructor
class PostMapper {
    private final CommentMapper commentMapper;
    public PostJpaEntity domainToEntity(Post post){
        return PostJpaEntity.builder()
                .title(post.getTitle())
                .content(post.getContent())
//                .comments(post.getComments().stream().map((comment)->commentMapper.domainToEntity(comment, post))
//                        .collect(Collectors.toList()))
                .build();
    }
    public Post entityToDomain(PostJpaEntity postJpaEntity){
        return Post.withId(new Post.PostId(postJpaEntity.getPostId()),
                postJpaEntity.getTitle(), postJpaEntity.getContent(),
                postJpaEntity.getComments().stream().map(commentMapper::entityToDomain)
                        .collect(Collectors.toList()));
    }
}
