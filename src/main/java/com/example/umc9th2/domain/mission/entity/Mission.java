package com.example.umc9th2.domain.mission.entity;

import com.example.umc9th2.domain.User.entity.mapping.UserMission;
import com.example.umc9th2.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mission")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;//PK

    @Column(nullable = false, length = 255)
    private String content;//미션 설명

    @Column(nullable = false)
    private LocalDate deadline;//종료일자

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;//생성일자

    /*
    연관관계
    mission(다) - store(1) 관계 테이블
    mission(1) - usermission(다) 관계 테이블
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;//FK-미션이 속한 가계

    //하나의 미션을 여러 사용자가 할 수 있다.
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<UserMission> userMissions = new ArrayList<>();
}
//머지 오류 수정중