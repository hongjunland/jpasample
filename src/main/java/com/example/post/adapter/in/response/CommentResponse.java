package com.example.post.adapter.in.response;

import lombok.Builder;

@Builder
public record CommentResponse(Long commentId, String content, Long postId) {
}
