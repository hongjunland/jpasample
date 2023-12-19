package com.example.post.application.port.out;

import com.example.post.domain.Comment;

public interface CreateCommentPort {
    boolean createComment(Comment comment);
}
