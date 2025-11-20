package com.example.UMC9th.domain.mission.dto;

import com.example.UMC9th.domain.mission.enums.MissonState;

public class MissionResDTO {

    public record MissionDTO(
            Long missionId,
            String missionContent,
            Integer point
    ) {}

    // 유저가 도전 중인 미션의 DTO
    public record ChallengeDTO(
            Integer userMissionId,   // UserMission PK (entity 에선 missionId)
            Long userId,
            Long missionId,
            MissonState missonState
    ) {}
}
