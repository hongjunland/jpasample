package com.example.post.application.port.in;

import com.example.post.adapter.in.response.PostResponse;

public interface GetPostQuery {
    PostResponse getPostById(Long posId);
}
