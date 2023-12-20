package com.example.post.application.service;

import com.example.common.annotation.UseCase;
import com.example.post.application.port.in.PostCreateUseCase;
import com.example.post.application.port.in.command.PostCreateCommand;
import com.example.post.application.port.out.CreatePostPort;
import com.example.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class CreatePostService implements PostCreateUseCase {
    private final CreatePostPort createPostPort;

    @Override
    public boolean createPost(PostCreateCommand postCreateCommand) {
        Post post = Post.builder()
                .title(postCreateCommand.title())
                .content(postCreateCommand.content())
                .build();
        createPostPort.createPost(post);
        return true;
    }
}
