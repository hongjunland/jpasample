package com.example.jpasample.post.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface SpringDataCommentRepository extends JpaRepository<CommentJpaEntity, Long> {
    List<CommentJpaEntity> findByPost(PostJpaEntity postJpaEntity);
    @Query("SELECT comment FROM CommentJpaEntity comment LEFT JOIN FETCH comment.parent WHERE comment.parent.commentId = :id")
    List<CommentJpaEntity> findByParentId(Long id);
}
