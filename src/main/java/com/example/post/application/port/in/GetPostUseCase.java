package com.example.post.application.port.in;

import com.example.post.adapter.in.response.PostResponse;

public interface GetPostUseCase {
    PostResponse getPostById(Long posId);
}
