package com.umc9th.peter.domain.store.entity;

import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.review.entity.Review;
import com.umc9th.peter.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_category_id", nullable = false)
    private StoreCategory storeCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Member owner;

    @Column(name = "open_at", nullable = false)
    private LocalTime openAt;

    @Column(name = "close_at", nullable = false)
    private LocalTime closeAt;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Review> storeReviewList = new ArrayList<>();

}
