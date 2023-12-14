package com.example.dto;

import lombok.Builder;

@Builder
public record CommentReadResponse(Long commentId, String content, Long postId) {
}
