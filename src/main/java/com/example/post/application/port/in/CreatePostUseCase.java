package com.example.post.application.port.in;

import com.example.post.application.port.in.command.PostCreateCommand;

public interface CreatePostUseCase {
    boolean createPost(PostCreateCommand postCreateCommand);
}
