package com.example.umc9th2.domain.mission.service.command;

import com.example.umc9th2.domain.User.entity.User;
import com.example.umc9th2.domain.User.entity.mapping.UserMission;
import com.example.umc9th2.domain.User.repository.UserMissionRepository;
import com.example.umc9th2.domain.User.repository.UserRepository;
import com.example.umc9th2.domain.mission.entity.Mission;
import com.example.umc9th2.domain.mission.repository.MissionRepository;
import com.example.umc9th2.global.apiPayload.exception.GeneralException;
import com.example.umc9th2.global.apiPayload.code.GeneralErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionCommandService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    private static final Long FIXED_USER_ID = 1L; // 로그인 없으므로 하드코딩

    public void challengeMission(Long missionId) {

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 유저 하드코딩
        User user = userRepository.findById(FIXED_USER_ID)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 이미 도전 중인지 체크
        if (userMissionRepository.existsByUserAndMission(user, mission)) {
            throw new GeneralException(GeneralErrorCode.INVALID_REQUEST);
        }

        UserMission userMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .isComplete(false)
                .build();

        userMissionRepository.save(userMission);
    }
}
