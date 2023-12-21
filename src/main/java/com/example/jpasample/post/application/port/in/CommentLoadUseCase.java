package com.example.jpasample.post.application.port.in;

import com.example.jpasample.post.adapter.in.web.response.CommentWithRepliesResponse;
import com.example.jpasample.post.application.port.in.command.CommentQuery;

public interface CommentLoadUseCase {
    CommentWithRepliesResponse getCommentListByParentId(CommentQuery commentQuery);
}
