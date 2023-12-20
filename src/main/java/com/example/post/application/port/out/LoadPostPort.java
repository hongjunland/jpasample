package com.example.post.application.port.out;

import com.example.post.domain.Post;

import java.util.List;

public interface LoadPostPort {
    Post loadById(Long id);
    List<Post> searchByTitle(String title);
}
