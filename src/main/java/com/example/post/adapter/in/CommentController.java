package com.example.post.adapter.in;

import com.example.common.annotation.WebAdapter;
import com.example.common.response.SuccessApiResponse;
import com.example.post.adapter.in.request.CommentCreateRequest;
import com.example.post.application.port.in.CommentCreateUseCase;
import com.example.post.application.port.in.CommentLoadUseCase;
import com.example.post.application.port.in.command.CommentCreateCommand;
import com.example.post.application.port.in.command.CommentReplyCreateCommand;
import com.example.post.application.port.in.command.CommentQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
class CommentController {
    private final CommentCreateUseCase createCommentUseCase;
    private final CommentLoadUseCase commentLoadUseCase;
    @PostMapping("/{postId}/comments")
    public SuccessApiResponse<?> createComment(@RequestBody CommentCreateRequest createRequest, @PathVariable Long postId){
        CommentCreateCommand commentCreateCommand = CommentCreateCommand.builder()
                .content(createRequest.content())
                .postId(postId)
                .build();
        return SuccessApiResponse.of(createCommentUseCase.createComment(commentCreateCommand));
    }
    @PostMapping("/{postId}/comments/{commentId}")
    public SuccessApiResponse<?> createReplyComment(@RequestBody CommentCreateRequest createRequest, @PathVariable Long postId, @PathVariable Long commentId){
        CommentReplyCreateCommand commentCreateCommand = CommentReplyCreateCommand.builder()
                .content(createRequest.content())
                .parentId(commentId)
                .postId(postId)
                .build();
        return SuccessApiResponse.of(createCommentUseCase.createCommentReply(commentCreateCommand));
    }
    @GetMapping("/{postId}/comments/{commentId}")
    public SuccessApiResponse<?> getCommentByCommentId(@PathVariable Long postId, @PathVariable Long commentId){
        CommentQuery getCommentQuery = CommentQuery.builder()
                .commentId(commentId)
                .postId(postId)
                .build();
        return SuccessApiResponse.of(commentLoadUseCase.getCommentListByParentId(getCommentQuery));
    }
}
