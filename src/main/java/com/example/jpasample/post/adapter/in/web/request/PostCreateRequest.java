package com.example.jpasample.post.adapter.in.web.request;

import lombok.Builder;

@Builder
public record PostCreateRequest(String title, String content) {
}
