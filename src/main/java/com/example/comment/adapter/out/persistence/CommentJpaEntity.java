package com.example.comment.adapter.out.persistence;

import com.example.post.adapter.out.persistence.PostJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private PostJpaEntity post;
}
