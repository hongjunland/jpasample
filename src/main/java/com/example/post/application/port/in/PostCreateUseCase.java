package com.example.post.application.port.in;

import com.example.post.application.port.in.command.PostCreateCommand;

public interface PostCreateUseCase {
    boolean createPost(PostCreateCommand postCreateCommand);
}
