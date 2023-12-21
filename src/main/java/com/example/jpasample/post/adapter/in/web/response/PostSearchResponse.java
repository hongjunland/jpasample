package com.example.jpasample.post.adapter.in.web.response;

import lombok.Builder;

@Builder
public record PostSearchResponse(Long postId, String title, String content) {
}
