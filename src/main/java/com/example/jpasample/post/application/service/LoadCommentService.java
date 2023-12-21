package com.example.jpasample.post.application.service;

import com.example.jpasample.common.annotation.UseCase;
import com.example.jpasample.post.adapter.in.web.response.CommentWithRepliesResponse;
import com.example.jpasample.post.domain.Comment;
import com.example.jpasample.post.adapter.in.web.response.CommentResponse;
import com.example.jpasample.post.application.port.in.CommentLoadUseCase;
import com.example.jpasample.post.application.port.in.command.CommentQuery;
import com.example.jpasample.post.application.port.out.LoadCommentPort;
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
