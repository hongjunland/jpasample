package com.example.dto;

import java.util.List;

public record PostItemReadResponse(Long postId, String title, String content, List<CommentReadResponse> comments) {
}
