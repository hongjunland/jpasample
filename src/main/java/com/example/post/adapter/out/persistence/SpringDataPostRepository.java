package com.example.post.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface SpringDataPostRepository extends JpaRepository<PostJpaEntity, Long> {
    @Override
    @Query("select b from PostJpaEntity b left join fetch b.comments")
    List<PostJpaEntity> findAll();
}
