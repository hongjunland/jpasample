package com.example.jpasample.post.application.port.in;

import com.example.jpasample.post.application.port.in.command.PostCreateCommand;

public interface PostCreateUseCase {
    boolean createPost(PostCreateCommand postCreateCommand);
}
