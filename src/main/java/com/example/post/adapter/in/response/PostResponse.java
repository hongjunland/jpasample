package com.example.post.adapter.in.response;

import com.example.comment.adapter.in.response.CommentResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record PostResponse(Long postId, String title, String content, List<CommentResponse> comment) {
}
