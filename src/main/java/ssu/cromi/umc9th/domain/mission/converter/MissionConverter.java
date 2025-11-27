package ssu.cromi.umc9th.domain.mission.converter;

import ssu.cromi.umc9th.domain.mission.dto.MissionReqDTO;
import ssu.cromi.umc9th.domain.mission.dto.MissionResDTO;
import ssu.cromi.umc9th.domain.mission.entity.Mission;
import ssu.cromi.umc9th.domain.mission.entity.UserMission;
import ssu.cromi.umc9th.domain.mission.enums.UserMissionStatus;
import ssu.cromi.umc9th.domain.user.entity.User;

public class MissionConverter {

    // Entity -> DTO
    public static MissionResDTO.ChallengeDTO toChallengeDTO(UserMission userMission) {
        return MissionResDTO.ChallengeDTO.builder()
                .userMissionId(userMission.getId())
                .userId(userMission.getUser().getUserId())
                .missionId(userMission.getMission().getId())
                .status(userMission.getStatus())
                .location(userMission.getLocation())
                .createdAt(userMission.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static UserMission toUserMission(MissionReqDTO.ChallengeDTO dto, User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status(UserMissionStatus.IN_PROGRESS)
                .location(dto.location())
                .build();
    }
}
