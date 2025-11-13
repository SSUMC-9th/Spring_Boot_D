package ssu.cromi.umc9th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import ssu.cromi.umc9th.domain.mission.enums.UserMissionStatus;
import ssu.cromi.umc9th.domain.user.entity.User;
import ssu.cromi.umc9th.global.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_mission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private UserMissionStatus status = UserMissionStatus.IN_PROGRESS;

    @Column
    private LocalDateTime completedAt;

    @Column
    private LocalDateTime expiredAt;

    @Column(nullable = false, length = 255)
    private String location;
}
