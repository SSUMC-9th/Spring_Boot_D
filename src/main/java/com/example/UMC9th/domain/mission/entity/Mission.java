package com.example.UMC9th.domain.mission.entity;

import com.example.UMC9th.domain.user.entity.mapping.UserMission;
import com.example.UMC9th.domain.store.entity.Store;
import com.example.UMC9th.global.auth.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mission")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    @Column(name = "misson_content", nullable = false, length = 255)
    private String missionContent;//미션 내용

    @Column(name = "point",nullable = false)
    private Integer point;

    @Column(name = "deadline")
    private LocalDate deadline;

    // 가게와 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    // 유저-미션 매핑 (진행중 / 완료 상태)
    @OneToMany(mappedBy = "mission")
    private List<UserMission> userMissions = new ArrayList<>();

}
