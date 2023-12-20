package com.example.post.application.port.out;

import com.example.post.domain.Comment;

public interface CreateCommentReplyPort {
    boolean createCommentReply(Comment comment);
}
