package com.example.jpasample.post.adapter.out.persistence;

import com.example.jpasample.common.annotation.PersistenceAdapter;
import com.example.jpasample.post.application.port.out.LoadCommentPort;
import com.example.jpasample.post.domain.Comment;
import lombok.RequiredArgsConstructor;

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
