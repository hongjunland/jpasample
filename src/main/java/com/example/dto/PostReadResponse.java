package com.example.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PostReadResponse(Long postId, String title, String content, List<CommentReadResponse> comment) {
}
