package com.example.post.application.service;


import com.example.common.annotation.UseCase;
import com.example.post.adapter.in.response.CommentResponse;
import com.example.post.adapter.in.response.PostResponse;
import com.example.post.application.port.in.GetPostUseCase;
import com.example.post.application.port.out.LoadPostPort;
import com.example.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;


@RequiredArgsConstructor
@UseCase
@Transactional
class LoadPostService implements GetPostUseCase {
    private final LoadPostPort loadPostPort;

    @Override
    public PostResponse getPostById(Long postId) {
        Post post = loadPostPort.loadById(postId);
        return PostResponse.builder()
                .postId(post.getId().value())
                .title(post.getTitle())
                .content(post.getContent())
                .comment(post.getComments().stream().map(
                        (comment) -> CommentResponse.builder()
                                .commentId(comment.getId().value())
                                .content(comment.getContent())
                                .postId(comment.getPostId().value())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
