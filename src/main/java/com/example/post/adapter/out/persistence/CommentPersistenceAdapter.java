package com.example.post.adapter.out.persistence;

import com.example.common.annotation.PersistenceAdapter;
import com.example.post.application.port.out.CreateCommentPort;
import com.example.post.application.port.out.CreateCommentReplyPort;
import com.example.post.domain.Comment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
class CommentPersistenceAdapter implements CreateCommentPort, CreateCommentReplyPort {
    private final SpringDataCommentRepository springDataCommentRepository;
    private final SpringDataPostRepository springDataPostRepository;
    private final CommentMapper commentMapper;
    @Transactional
    @Override
    public boolean createComment(Comment comment) {
        PostJpaEntity postJpaEntity = springDataPostRepository.findById(comment.getPostId().value())
                .orElseThrow(RuntimeException::new);
        CommentJpaEntity commentJpaEntity = commentMapper.domainToEntity(comment, postJpaEntity);
        postJpaEntity.createComment(commentJpaEntity);
        springDataPostRepository.save(postJpaEntity);
        return true;
    }

    @Transactional
    @Override
    public boolean createCommentReply(Comment comment) {
        PostJpaEntity postJpaEntity = springDataPostRepository.findById(comment.getPostId().value())
                .orElseThrow(RuntimeException::new);
        CommentJpaEntity parentCommentJpaEntity = springDataCommentRepository.findById(comment.getParentId().value())
                .orElseThrow(RuntimeException::new);
        CommentJpaEntity commentJpaEntity = commentMapper.domainToEntityWithParent(comment, parentCommentJpaEntity, postJpaEntity);
        parentCommentJpaEntity.addReply(commentJpaEntity);
        springDataCommentRepository.save(parentCommentJpaEntity);
        return true;
    }
}
