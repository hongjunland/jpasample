package com.example.post.application.port.in;

import com.example.post.adapter.in.response.CommentWithRepliesResponse;
import com.example.post.application.port.in.command.CommentQuery;

import java.util.List;

public interface CommentLoadUseCase {
    CommentWithRepliesResponse getCommentListByParentId(CommentQuery commentQuery);
}
