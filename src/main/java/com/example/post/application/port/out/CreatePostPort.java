package com.example.post.application.port.out;

import com.example.post.domain.Post;

public interface CreatePostPort {
    boolean createPost(Post post);
}
