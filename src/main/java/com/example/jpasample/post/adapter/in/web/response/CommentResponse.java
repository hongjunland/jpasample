package com.example.jpasample.post.adapter.in.web.response;

import lombok.Builder;

@Builder
public record CommentResponse(Long commentId, String content, Long postId, Long parentId) {
}
