package com.example.post.adapter.in.response;

import com.example.comment.adapter.in.response.CommentResponse;

import java.util.List;

public record PostItemReadResponse(Long postId, String title, String content, List<CommentResponse> comments) {
}
