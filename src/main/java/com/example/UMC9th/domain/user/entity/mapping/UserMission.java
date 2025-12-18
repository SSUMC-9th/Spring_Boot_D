package com.example.UMC9th.domain.user.entity.mapping;

import com.example.UMC9th.domain.user.entity.User;
import com.example.UMC9th.domain.mission.entity.Mission;
import com.example.UMC9th.domain.mission.enums.MissonState;
import com.example.UMC9th.global.auth.enums.SocialType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_mission")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UserMissionId;

    @Column(name = "misson_state", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private MissonState missionState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)//FK
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)//FK
    private Mission mission;
}
