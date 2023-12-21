package com.example.jpasample.post.application.port.in.command;

import lombok.Builder;

@Builder
public record CommentCreateCommand(Long postId, String content) {
}
