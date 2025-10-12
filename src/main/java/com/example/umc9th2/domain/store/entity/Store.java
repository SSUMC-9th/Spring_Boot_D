package com.example.umc9th2.domain.store.entity;

import com.example.umc9th2.domain.mission.entity.Mission;
import com.example.umc9th2.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @Column(nullable = false, length = 100)
    private String name; // 가게 이름

    @Column(nullable = false, length = 200)
    private String address; // 가게 주소

    @Column(nullable = false)
    private LocalDate businessHours; // 영업 시간

    @Column(nullable = false)
    private Long ownerNumber; // 사장님 구분 번호

    /*
     * 연관관계
     * 한 가게는 여러 개의 미션을 가질 수 있다.
     * 한 가게는 여러 개의 리뷰를 가질 수 있다.
     * 여러 가게는 하나의 지역(Location)에 속할 수 있다.
     */
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    // ✅ Location과 다대일 관계 추가 (에러 해결 핵심 부분)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location; // FK (하나의 지역에 여러 가게가 속함)
}