package com.example.umc9th2.domain.review.entity;

import com.example.umc9th2.domain.User.entity.User;
import com.example.umc9th2.domain.store.entity.Store;
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
    private Long reviewId;//PK

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;//리뷰내용

    @Column(nullable = false)
    private Float score;//별점

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;//생성일자

    /*
    연관관계
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)//FK
    private Store store;//하나의 리뷰는 하나의 가게에 속한다

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)//FK
    private User user;//한 명의 사용자가 하나의 리뷰 작성

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)//reply 테이블의 FK
    private List<Reply> replies = new ArrayList<>();//하나의 리뷰에 여러 개의 사장님 댓글

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)//reviewphpto테이블의 FK
    private List<ReviewPhoto> reviewPhotos = new ArrayList<>();
}
