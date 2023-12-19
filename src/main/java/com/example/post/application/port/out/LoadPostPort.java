package com.example.post.application.port.out;

import com.example.post.domain.Post;

public interface LoadPostPort {
    Post loadById(Long id);
}
