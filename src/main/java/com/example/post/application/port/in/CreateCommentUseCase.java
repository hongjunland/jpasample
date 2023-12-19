package com.example.post.application.port.in;

import com.example.post.application.port.in.command.CommentCreateCommand;

public interface CreateCommentUseCase {
    boolean createComment(CommentCreateCommand commentCreateCommand);
}
