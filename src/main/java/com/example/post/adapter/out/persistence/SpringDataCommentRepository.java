package com.example.post.adapter.out.persistence;

import com.example.post.adapter.out.persistence.CommentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
interface SpringDataCommentRepository extends JpaRepository<CommentJpaEntity, Long> {
    List<CommentJpaEntity> findByPost(PostJpaEntity postJpaEntity);
}
