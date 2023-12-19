package com.example.post.adapter.out.persistence;

import com.example.common.annotation.Mapper;
import com.example.post.domain.Comment;
import com.example.post.domain.Post;

@Mapper
class CommentMapper {
    public CommentJpaEntity domainToEntity(Comment comment, PostJpaEntity postJpaEntity){
        return CommentJpaEntity.builder()
                .content(comment.getContent())
                .post(postJpaEntity)
                .build();
    }
    public Comment entityToDomain(CommentJpaEntity commentJpaEntity){
        return Comment.withId(new Comment.CommentId(commentJpaEntity.getCommentId()),
                commentJpaEntity.getContent(),
                new Post.PostId(commentJpaEntity.getPost().getPostId()));
    }
}
