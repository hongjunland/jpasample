package com.example.post.domain;

import com.example.comment.domain.Comment;
import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class Post {
    private final PostId id;
    private final String title;
    private final String content;
    private final List<Comment> comments;
    public static Post withId(PostId postId, String title, String content, List<Comment> comments){
        return Post.builder()
                .id(postId)
                .title(title)
                .content(content)
                .comments(comments)
                .build();
    }
    public static Post withoutId(String title, String content){
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
    public record PostId(Long value){}
}
