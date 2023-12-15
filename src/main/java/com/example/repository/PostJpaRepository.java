package com.example.repository;

import com.example.entity.PostJpaEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<PostJpaEntity, Long> {
    @Override
    @Query("select b from PostJpaEntity b join fetch b.comments")
    List<PostJpaEntity> findAll();

    @Override
    long count();
}
