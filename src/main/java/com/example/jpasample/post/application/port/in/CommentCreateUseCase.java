package com.example.jpasample.post.application.port.in;

import com.example.jpasample.post.application.port.in.command.CommentReplyCreateCommand;
import com.example.jpasample.post.application.port.in.command.CommentCreateCommand;

public interface CommentCreateUseCase {
    boolean createComment(CommentCreateCommand commentCreateCommand);
    boolean createCommentReply(CommentReplyCreateCommand commentCreateCommand);
}
