package com.umc9th.peter.domain.member.dto;

import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.member.entity.mapping.MemberMission;
import com.umc9th.peter.domain.member.enums.MissionStatus;
import com.umc9th.peter.domain.mission.dto.MissionResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResponse {

    public record joinDto(
            Long memberId,
            LocalDateTime createdAt
    ) {

        public static joinDto fromEntity(Member member) {
            return new joinDto(
                    member.getId(),
                    member.getCreatedAt()
            );
        }

    }

    public record loginDto(
            Long memberId,
            String accessToken
    ) {

    }

    public record MissionDto(
            Long id,
            MissionResponse.MissionDto mission,
            MissionStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {

        public static MissionDto fromEntity(MemberMission memberMission) {
            return new MissionDto(
                    memberMission.getId(),
                    MissionResponse.MissionDto.fromEntity(memberMission.getMission()),
                    memberMission.getStatus(),
                    memberMission.getCreatedAt(),
                    memberMission.getUpdatedAt()
            );
        }

    }

    @Builder
    public record MissionListDto(
            List<MissionDto> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

}
