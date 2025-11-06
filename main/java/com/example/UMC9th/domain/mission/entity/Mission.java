package com.example.UMC9th.domain.mission.entity;

import com.example.UMC9th.domain.user.entity.mapping.UserMission;
import com.example.UMC9th.domain.store.entity.Store;
import com.example.UMC9th.global.auth.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    private Long missonId;

    @Column(name = "misson_content", nullable = false, length = 255)
    private String missonContent;//미션 내용

    @Column(name = "point",nullable = false)
    private Integer point;


}
