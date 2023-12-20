package com.example.post.application.port.in;

import com.example.post.adapter.in.web.response.CommentWithRepliesResponse;
import com.example.post.application.port.in.command.CommentQuery;

public interface CommentLoadUseCase {
    CommentWithRepliesResponse getCommentListByParentId(CommentQuery commentQuery);
}
