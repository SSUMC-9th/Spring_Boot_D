package com.example.UMC9th.domain.review.entity;

import com.example.UMC9th.domain.user.entity.User;
import com.example.UMC9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "review")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(name = "review_value",nullable = false)
    private Float reviewValue;

    @Column(name = "review_content",nullable = false)
    private String reviewContent;

    @CreatedDate
    @Column(name ="created_at", nullable = false)
    private LocalDateTime createdAt;//생성일자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)//FK
    private Store store;//가게에 여러개의 리뷰가 있음

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)//FK
    private User user;//사용자 하나가 여러 리뷰 작성 가능
}
