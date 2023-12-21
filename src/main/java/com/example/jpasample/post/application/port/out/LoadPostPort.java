package com.example.jpasample.post.application.port.out;

import com.example.jpasample.post.domain.Post;

import java.util.List;

public interface LoadPostPort {
    Post loadById(Long id);
    List<Post> searchByTitle(String title);
}
