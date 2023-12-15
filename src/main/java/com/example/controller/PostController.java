package com.example.controller;

import com.example.service.PostService;
import com.example.dto.PostCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody @Valid PostCreateRequest createRequest){
        return ResponseEntity.ok(postService.createPost(createRequest));
    }
    @GetMapping
    public ResponseEntity<?> getPostList(){
        return ResponseEntity.ok(postService.findPostList());
    }
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId){
        return ResponseEntity.ok(postService.findByPostId(postId));
    }
    @GetMapping("/count")
    public ResponseEntity<?> getCount(){
        return ResponseEntity.ok(postService.getCount());
    }
    @DeleteMapping
    public void deleteList(){postService.deleteAll();}
}
