package com.example.post.adapter.out.persistence;

import com.example.common.annotation.PersistenceAdapter;
import com.example.post.application.port.out.LoadCommentPort;
import com.example.post.domain.Comment;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
class CommentLoadPersistenceAdapter implements LoadCommentPort {
    private final SpringDataCommentRepository springDataCommentRepository;
    private final CommentMapper commentMapper;
    @Override
    public Comment loadById(Long id) {
        CommentJpaEntity commentJpaEntity = springDataCommentRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return commentMapper.entityToDomainWithReplies(commentJpaEntity);
    }
}
