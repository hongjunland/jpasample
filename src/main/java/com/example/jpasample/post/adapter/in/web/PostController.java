package com.example.jpasample.post.adapter.in.web;

import com.example.jpasample.common.annotation.WebAdapter;
import com.example.jpasample.common.response.SuccessApiResponse;
import com.example.jpasample.post.adapter.in.web.request.PostCreateRequest;
import com.example.jpasample.post.application.port.in.PostCreateUseCase;
import com.example.jpasample.post.application.port.in.command.PostCreateCommand;
import com.example.jpasample.post.application.port.in.command.PostQuery;
import com.example.jpasample.post.application.port.in.command.PostSearchByTitleQuery;
import com.example.jpasample.post.application.port.in.PostLoadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
class PostController {
    private final PostCreateUseCase createPostUseCase;
    private final PostLoadUseCase getPostUseCase;
    @PostMapping
    public SuccessApiResponse<?> createPost(@RequestBody PostCreateRequest createRequest){
        PostCreateCommand command = PostCreateCommand.builder()
                .title(createRequest.title())
                .content(createRequest.content())
                .build();
        return SuccessApiResponse.of(createPostUseCase.createPost(command));
    }
    @GetMapping("/{postId}")
    public SuccessApiResponse<?> getPostById(@PathVariable Long postId){
        PostQuery postQuery = PostQuery.builder()
                .postId(postId)
                .build();
        return SuccessApiResponse.of(getPostUseCase.getPostById(postQuery));
    }
    @GetMapping
    public SuccessApiResponse<?> getPostListByTitle(@RequestParam String title){
        PostSearchByTitleQuery postSearchByTitleQuery = PostSearchByTitleQuery.builder()
                .title(title)
                .build();
        return SuccessApiResponse.of(getPostUseCase.searchPostListByTitle(postSearchByTitleQuery));
    }
}
