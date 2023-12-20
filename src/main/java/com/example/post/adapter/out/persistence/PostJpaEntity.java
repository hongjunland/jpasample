package com.example.post.adapter.out.persistence;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CommentJpaEntity> comments = new ArrayList<>();

    public void createComment(CommentJpaEntity commentJpaEntity){
        comments.add(commentJpaEntity);
    }
}
