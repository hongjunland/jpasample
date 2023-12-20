package com.example.post.application.service;

import com.example.common.annotation.UseCase;
import com.example.post.adapter.in.web.response.CommentResponse;
import com.example.post.adapter.in.web.response.CommentWithRepliesResponse;
import com.example.post.application.port.in.CommentLoadUseCase;
import com.example.post.application.port.in.command.CommentQuery;
import com.example.post.application.port.out.LoadCommentPort;
import com.example.post.domain.Comment;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@UseCase
class LoadCommentService implements CommentLoadUseCase {
    private final LoadCommentPort loadCommentPort;
    @Override
    public CommentWithRepliesResponse getCommentListByParentId(CommentQuery commentQuery) {
        Comment comment = loadCommentPort.loadById(commentQuery.commentId());

        return CommentWithRepliesResponse.builder()
                .commentId(comment.getId().value())
                .content(comment.getContent())
                .postId(comment.getPostId().value())
                .parentId(comment.getParentId().value())
                .replies(comment.getReplies().stream()
                        .map((domain)-> CommentResponse.builder()
                                .commentId(domain.getId().value())
                                .content(domain.getContent())
                                .postId(domain.getPostId().value())
                                .parentId(domain.getParentId().value())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
