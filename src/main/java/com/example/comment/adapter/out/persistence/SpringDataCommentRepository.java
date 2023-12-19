package com.example.comment.adapter.out.persistence;

import com.example.post.adapter.out.persistence.PostJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface SpringDataCommentRepository extends JpaRepository<CommentJpaEntity, Long> {
    List<CommentJpaEntity> findByPost(PostJpaEntity postJpaEntity);
}
