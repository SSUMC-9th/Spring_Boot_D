package com.example.UMC9th.domain.mission.converter;


import com.example.UMC9th.domain.mission.dto.MissionReqDTO;
import com.example.UMC9th.domain.mission.dto.MissionResDTO;
import com.example.UMC9th.domain.mission.entity.Mission;
import com.example.UMC9th.domain.mission.enums.MissonState;
import com.example.UMC9th.domain.user.entity.User;
import com.example.UMC9th.domain.user.entity.mapping.UserMission;

public class MissionConverter {

    // DTO + User + Mission -> UserMission 생성
    public static UserMission toUserMission(
            MissionReqDTO.ChallengeDTO dto,
            User user,
            Mission mission
    ) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .missonState(MissonState.INPROGRESS)
                .build();
    }

    // UserMission -> 응답 DTO
    public static MissionResDTO.ChallengeDTO toChallengeDTO(
            UserMission userMission
    ) {
        return new MissionResDTO.ChallengeDTO(
                userMission.getMissionId(),
                userMission.getUser().getUserId(),
                userMission.getMission().getMissonId(),
                userMission.getMissonState()
        );
    }
}
