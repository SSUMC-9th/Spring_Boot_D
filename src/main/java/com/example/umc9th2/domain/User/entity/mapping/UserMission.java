package com.example.umc9th2.domain.User.entity.mapping;

import com.example.umc9th2.domain.User.entity.User;
import com.example.umc9th2.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_mission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMissionId;//PK

    @Column(name = "isComplete", nullable = false)
    private Boolean isComplete = false;//성공여부

    /*
    연관 관계
    user-mission 다대다 관계 테이블의 중간 테이블(user_mission)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)//FK
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)//FK
    private Mission mission;
}
