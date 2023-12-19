package com.example.post.adapter.out.persistence;


import com.example.comment.adapter.out.persistence.CommentJpaEntity;
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
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CommentJpaEntity> comments;

    public void createComment(CommentJpaEntity commentJpaEntity){
        comments.add(commentJpaEntity);
    }
}
