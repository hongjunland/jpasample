package com.example.post.application.port.in;

import com.example.post.application.port.in.command.CommentCreateCommand;
import com.example.post.application.port.in.command.CommentReplyCreateCommand;

public interface CommentCreateUseCase {
    boolean createComment(CommentCreateCommand commentCreateCommand);
    boolean createCommentReply(CommentReplyCreateCommand commentCreateCommand);
}
