package com.example.post.application.port.in;

import com.example.post.adapter.in.web.response.PostResponse;
import com.example.post.application.port.in.command.PostQuery;
import com.example.post.application.port.in.command.PostSearchByTitleQuery;

import java.util.List;

public interface PostLoadUseCase {
    PostResponse getPostById(PostQuery postQuery);
    List<PostResponse> searchPostListByTitle(PostSearchByTitleQuery postSearchByTitleQuery);
}
