package com.example.jpasample.post.adapter.in.web.request;

import lombok.Builder;

@Builder
public record CommentCreateRequest(String content) {
}
