package com.example.repository;

import com.example.entity.CommentJpaEntity;
import com.example.entity.PostJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<CommentJpaEntity, Long> {
    List<CommentJpaEntity> findByPost(PostJpaEntity postJpaEntity);
}
