package com.example.jpasample.post.application.port.in.command;

import lombok.Builder;

@Builder
public record CommentQuery(Long postId, Long commentId, String content) {
}
