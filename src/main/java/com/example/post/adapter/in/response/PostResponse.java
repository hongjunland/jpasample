package com.example.post.adapter.in.response;

import lombok.Builder;

import java.util.List;

@Builder
public record PostResponse(Long postId, String title, String content, List<CommentResponse> comment) {
}
