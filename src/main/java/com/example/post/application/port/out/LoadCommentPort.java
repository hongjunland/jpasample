package com.example.post.application.port.out;

import com.example.post.domain.Comment;

public interface LoadCommentPort {
    Comment loadById(Long id);
}
