package com.example.jpasample.post.application.port.out;

import com.example.jpasample.post.domain.Post;

public interface CreatePostPort {
    boolean createPost(Post post);
}
