package com.example.post.adapter.in.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CommentWithRepliesResponse(Long commentId, String content, Long postId, Long parentId, List<CommentResponse> replies) {
}
