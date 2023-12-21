package com.example.jpasample.post.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "comments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CommentJpaEntity parent; // 부모
//    @BatchSize(size = 20)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<CommentJpaEntity> replies = new ArrayList<>(); // 대댓글

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostJpaEntity post;

    // 대댓글 추가 메서드
    public void addReply(CommentJpaEntity reply) {
        replies.add(reply);
    }
}
