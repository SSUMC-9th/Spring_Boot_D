package com.example.umc9th2.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reply")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Column(nullable = false, length = 300)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;
}
