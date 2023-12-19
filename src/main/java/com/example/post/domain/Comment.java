package com.example.post.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class Comment {
    private final CommentId id;
    private final String content;
    private final Post.PostId postId;
    public static Comment withId(CommentId id, String content, Post.PostId postId){
        return Comment.builder()
                .id(id)
                .content(content)
                .postId(postId)
                .build();
    }
    public static Comment withoutId(String content, Post.PostId postId){
        return Comment.builder()
                .content(content)
                .postId(postId)
                .build();
    }
    public record CommentId(Long value){}
}
