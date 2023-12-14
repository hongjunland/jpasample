package com.example.controller;

import com.example.dto.CommentCreateRequest;
import com.example.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/{postId}/comments")
    public ResponseEntity<?> createComment(@PathVariable Long postId, @RequestBody CommentCreateRequest commentCreateRequest){
        return ResponseEntity.ok(commentService.createComment(postId, commentCreateRequest));
    }
    @GetMapping("/{postId}/comments")
    public ResponseEntity<?> getCommentList(@PathVariable Long postId){
        return ResponseEntity.ok(commentService.getCommentList(postId));
    }
}
