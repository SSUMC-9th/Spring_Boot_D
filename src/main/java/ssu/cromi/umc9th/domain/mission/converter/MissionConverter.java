package ssu.cromi.umc9th.domain.mission.converter;

import org.springframework.data.domain.Page;
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

    // 특정 가게의 미션 목록 조회 - Page<Mission> -> StoreMissionListDTO
    public static MissionResDTO.StoreMissionListDTO toStoreMissionListDTO(Page<Mission> result) {
        return MissionResDTO.StoreMissionListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toStoreMissionDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    // Mission -> StoreMissionDTO
    public static MissionResDTO.StoreMissionDTO toStoreMissionDTO(Mission mission) {
        return MissionResDTO.StoreMissionDTO.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())
                .content(mission.getContent())
                .point(mission.getPoint())
                .dueDate(mission.getDueDate())
                .status(mission.getStatus())
                .build();
    }

    // 특정 유저가 진행중인 미션 목록 조회 - Page<UserMission> -> UserMissionListDTO
    public static MissionResDTO.UserMissionListDTO toUserMissionListDTO(Page<UserMission> result) {
        return MissionResDTO.UserMissionListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toUserMissionDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    // UserMission -> UserMissionDTO
    public static MissionResDTO.UserMissionDTO toUserMissionDTO(UserMission userMission) {
        return MissionResDTO.UserMissionDTO.builder()
                .userMissionId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .storeName(userMission.getMission().getStore().getStoreName())
                .title(userMission.getMission().getTitle())
                .content(userMission.getMission().getContent())
                .point(userMission.getMission().getPoint())
                .dueDate(userMission.getMission().getDueDate())
                .status(userMission.getStatus())
                .location(userMission.getLocation())
                .createdAt(userMission.getCreatedAt())
                .build();
    }
}
