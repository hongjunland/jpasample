package com.example.post.application.service;

import com.example.common.annotation.UseCase;
import com.example.post.application.port.in.CreatePostUseCase;
import com.example.post.application.port.in.command.PostCreateCommand;
import com.example.post.application.port.out.CreatePostPort;
import com.example.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class CreatePostService implements CreatePostUseCase {
    private final CreatePostPort createPostPort;

    @Override
    public boolean createPost(PostCreateCommand postCreateCommand) {
        Post post = Post.withoutId(postCreateCommand.title(), postCreateCommand.content());
        createPostPort.createPost(post);
        return false;
    }
}
