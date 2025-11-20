package com.umc9th.peter.domain.mission.service;

import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.member.entity.mapping.MemberMission;
import com.umc9th.peter.domain.member.enums.MissionStatus;
import com.umc9th.peter.domain.member.exception.MemberException;
import com.umc9th.peter.domain.member.exception.code.MemberErrorCode;
import com.umc9th.peter.domain.member.repository.MemberMissionRepository;
import com.umc9th.peter.domain.member.repository.MemberRepository;
import com.umc9th.peter.domain.mission.dto.MissionResponse;
import com.umc9th.peter.domain.mission.entity.Mission;
import com.umc9th.peter.domain.mission.exception.MissionException;
import com.umc9th.peter.domain.mission.exception.code.MissionErrorCode;
import com.umc9th.peter.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MissionResponse.AcceptDto acceptMission(
            Long memberId,
            Long missionId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        if (mission.getEndAt().isBefore(LocalDateTime.now())) {
            throw new MissionException(MissionErrorCode.CLOSED);
        } else if (mission.getBeginAt().isAfter(LocalDateTime.now())) {
            throw new MissionException(MissionErrorCode.NOT_OPENED);
        }

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.ACCEPTED)
                .build();

        memberMissionRepository.save(memberMission);

        return MissionResponse.AcceptDto.fromEntity(memberMission);
    }

}
