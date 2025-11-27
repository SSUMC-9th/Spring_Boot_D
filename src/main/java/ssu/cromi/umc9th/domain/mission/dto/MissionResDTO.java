package ssu.cromi.umc9th.domain.mission.dto;

import lombok.Builder;
import ssu.cromi.umc9th.domain.mission.enums.UserMissionStatus;

import java.time.LocalDateTime;

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
}
