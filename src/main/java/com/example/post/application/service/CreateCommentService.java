package com.example.post.application.service;

import com.example.common.annotation.UseCase;
import com.example.post.application.port.in.CommentCreateUseCase;
import com.example.post.application.port.in.command.CommentCreateCommand;
import com.example.post.application.port.in.command.CommentReplyCreateCommand;
import com.example.post.application.port.out.CreateCommentPort;
import com.example.post.application.port.out.CreateCommentReplyPort;
import com.example.post.domain.Comment;
import com.example.post.domain.Post;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
@Transactional
class CreateCommentService implements CommentCreateUseCase {
    private final CreateCommentPort createCommentPort;
    private final CreateCommentReplyPort createCommentReply;
    @Override
    public boolean createComment(CommentCreateCommand commentCreateCommand) {
        Comment comment = Comment.builder()
                .content(commentCreateCommand.content())
                .postId(new Post.PostId(commentCreateCommand.postId()))
                .build();
        createCommentPort.createComment(comment);
        return true;
    }

    @Override
    public boolean createCommentReply(CommentReplyCreateCommand commentCreateCommand) {
        Comment comment = Comment.builder()
                .content(commentCreateCommand.content())
                .postId(new Post.PostId(commentCreateCommand.postId()))
                .parentId(new Comment.CommentId(commentCreateCommand.parentId()))
                .build();
        createCommentReply.createCommentReply(comment);
        return true;
    }
}
