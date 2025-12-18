package com.umc9th.peter.domain.member.converter;

import com.umc9th.peter.domain.member.dto.MemberResponse;
import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.member.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MemberConverter {

    public static MemberResponse.MissionListDto toMissionListDto(
            Page<MemberMission> result
    ) {
        List<MemberResponse.MissionDto> missionDtos = result.stream()
                .map(MemberResponse.MissionDto::fromEntity)
                .toList();

        return MemberResponse.MissionListDto.builder()
                .missionList(missionDtos)
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MemberResponse.loginDto toLoginDto(Member member, String accessToken) {
        return new MemberResponse.loginDto(
                member.getId(),
                accessToken
        );
    }

}
