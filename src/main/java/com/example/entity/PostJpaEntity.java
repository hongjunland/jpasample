package com.example.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Table(name = "posts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long postId;
    private String title;
    private String content;

//    @OneToMany
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentJpaEntity> comments;

    public void createComment(CommentJpaEntity commentJpaEntity){
        comments.add(commentJpaEntity);
    }
}
