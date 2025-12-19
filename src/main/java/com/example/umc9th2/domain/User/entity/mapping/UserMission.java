package com.example.umc9th2.domain.User.entity.mapping;

import com.example.umc9th2.domain.User.entity.User;
import com.example.umc9th2.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_mission")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMission {

    //미션 진행중, 완료 상태, 진행전인 것도 함께 넘겨줘야 함

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMissionId;//PK

    @Builder.Default//빌더를 사용할때 펄스가 자동으로 들어가게
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
