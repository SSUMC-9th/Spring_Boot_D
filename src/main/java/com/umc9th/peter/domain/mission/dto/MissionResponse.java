package com.umc9th.peter.domain.mission.dto;

import com.umc9th.peter.domain.member.entity.mapping.MemberMission;

import java.time.LocalDateTime;

public class MissionResponse {

    public record AcceptDto(
            Long id,
            LocalDateTime createdAt
    ) {

        public static MissionResponse.AcceptDto fromEntity(MemberMission memberMission) {
            return new MissionResponse.AcceptDto(
                    memberMission.getId(),
                    memberMission.getCreatedAt()
            );
        }

    }

}
