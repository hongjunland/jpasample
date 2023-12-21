package com.example.jpasample.post.application.port.out;

import com.example.jpasample.post.domain.Comment;

public interface CreateCommentPort {
    boolean createComment(Comment comment);
}
