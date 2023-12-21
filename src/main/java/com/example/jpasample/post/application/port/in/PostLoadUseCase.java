package com.example.jpasample.post.application.port.in;

import com.example.jpasample.post.adapter.in.web.response.PostResponse;
import com.example.jpasample.post.adapter.in.web.response.PostSearchResponse;
import com.example.jpasample.post.application.port.in.command.PostQuery;
import com.example.jpasample.post.application.port.in.command.PostSearchByTitleQuery;

import java.util.List;

public interface PostLoadUseCase {
    PostResponse getPostById(PostQuery postQuery);
    List<PostSearchResponse> searchPostListByTitle(PostSearchByTitleQuery postSearchByTitleQuery);
}
