package com.example.post.adapter.out.persistence;

import com.example.common.annotation.PersistenceAdapter;
import com.example.post.application.port.out.CreateCommentPort;
import com.example.post.domain.Comment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
class CommentPersistenceAdapter implements CreateCommentPort {
    private final SpringDataCommentRepository springDataCommentRepository;
    private final SpringDataPostRepository springDataPostRepository;
    private final CommentMapper commentMapper;
    @Override
    @Transactional
    public boolean createComment(Comment comment) {
        PostJpaEntity postJpaEntity = springDataPostRepository.findById(comment.getPostId().value())
                .orElseThrow(RuntimeException::new);
        CommentJpaEntity commentJpaEntity = commentMapper.domainToEntity(comment, postJpaEntity);
        postJpaEntity.createComment(commentJpaEntity);
        springDataPostRepository.save(postJpaEntity);
        return true;
    }
}
