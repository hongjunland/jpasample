package com.example.post.adapter.in;

import com.example.common.annotation.WebAdapter;
import com.example.common.response.SuccessApiResponse;
import com.example.post.adapter.in.request.PostCreateRequest;
import com.example.post.application.port.in.CreatePostUseCase;
import com.example.post.application.port.in.GetPostUseCase;
import com.example.post.application.port.in.command.PostCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
class PostController {
    private final CreatePostUseCase createPostUseCase;
    private final GetPostUseCase getPostUseCase;
    @PostMapping
    public SuccessApiResponse<?> createPost(@RequestBody PostCreateRequest createRequest){
        PostCreateCommand command = PostCreateCommand.builder()
                .title(createRequest.title())
                .content(createRequest.content())
                .build();
        return SuccessApiResponse.of(createPostUseCase.createPost(command));
    }
    @GetMapping("{postId}")
    public SuccessApiResponse<?> getPost(@PathVariable Long postId){
        return SuccessApiResponse.of(getPostUseCase.getPostById(postId));
    }
}
