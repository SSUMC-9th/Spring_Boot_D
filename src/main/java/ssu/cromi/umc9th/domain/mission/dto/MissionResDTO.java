package ssu.cromi.umc9th.domain.mission.dto;

import lombok.Builder;
import ssu.cromi.umc9th.domain.mission.enums.MissionStatus;
import ssu.cromi.umc9th.domain.mission.enums.UserMissionStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record ChallengeDTO(
            Long userMissionId,
            Long userId,
            Long missionId,
            UserMissionStatus status,
            String location,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record StoreMissionListDTO(
            List<StoreMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record StoreMissionDTO(
            Long missionId,
            String title,
            String content,
            Long point,
            LocalDate dueDate,
            MissionStatus status
    ) {}

    @Builder
    public record UserMissionListDTO(
            List<UserMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record UserMissionDTO(
            Long userMissionId,
            Long missionId,
            String storeName,
            String title,
            String content,
            Long point,
            LocalDate dueDate,
            UserMissionStatus status,
            String location,
            LocalDateTime createdAt
    ) {}
}
