package com.example.post.application.port.in;

import com.example.post.adapter.in.web.response.PostResponse;
import com.example.post.application.port.in.command.PostQuery;

public interface PostLoadUseCase {
    PostResponse getPostById(PostQuery postQuery);
}
