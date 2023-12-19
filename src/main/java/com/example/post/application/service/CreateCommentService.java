package com.example.post.application.service;

import com.example.common.annotation.UseCase;
import com.example.post.application.port.in.CreateCommentUseCase;
import com.example.post.application.port.in.command.CommentCreateCommand;
import com.example.post.application.port.out.CreateCommentPort;
import com.example.post.domain.Comment;
import com.example.post.domain.Post;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
@Transactional
class CreateCommentService implements CreateCommentUseCase {
    private final CreateCommentPort createCommentPort;
    @Override
    public boolean createComment(CommentCreateCommand commentCreateCommand) {
        Comment comment = Comment.withoutId(commentCreateCommand.content(),
                new Post.PostId(commentCreateCommand.postId()));
        createCommentPort.createComment(comment);
        return true;
    }
}
