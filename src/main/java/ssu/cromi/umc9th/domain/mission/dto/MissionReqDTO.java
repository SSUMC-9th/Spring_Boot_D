package ssu.cromi.umc9th.domain.mission.dto;

public class MissionReqDTO {
    public record ChallengeDTO(
            Long userId,
            String location
    ) {}
}
