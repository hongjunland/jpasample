package com.example.post.adapter.in;

import com.example.common.annotation.WebAdapter;
import com.example.common.response.SuccessApiResponse;
import com.example.post.adapter.in.request.CommentCreateRequest;
import com.example.post.application.port.in.CreateCommentUseCase;
import com.example.post.application.port.in.command.CommentCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
class CommentController {
    private final CreateCommentUseCase createCommentUseCase;
    @PostMapping("/{postId}/comments")
    public SuccessApiResponse<?> createComment(@RequestBody CommentCreateRequest createRequest, @PathVariable Long postId){
        CommentCreateCommand commentCreateCommand = CommentCreateCommand.builder()
                .content(createRequest.content())
                .postId(postId)
                .build();
        return SuccessApiResponse.of(createCommentUseCase.createComment(commentCreateCommand));
    }
}
