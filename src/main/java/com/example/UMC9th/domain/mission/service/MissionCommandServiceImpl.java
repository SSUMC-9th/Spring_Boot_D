//package com.example.UMC9th.domain.mission.service.command;
//
//import com.example.UMC9th.domain.mission.converter.MissionConverter;
//import com.example.UMC9th.domain.mission.dto.MissionReqDTO;
//import com.example.UMC9th.domain.mission.dto.MissionResDTO;
//import com.example.UMC9th.domain.mission.entity.Mission;
//import com.example.UMC9th.domain.mission.exception.MissionException;
//import com.example.UMC9th.domain.mission.exception.code.MissionErrorCode;
//import com.example.UMC9th.domain.mission.repository.MissionRepository;
//import com.example.UMC9th.domain.user.entity.User;
//import com.example.UMC9th.domain.user.entity.mapping.UserMission;
//import com.example.UMC9th.domain.user.exception.UserException;
//import com.example.UMC9th.domain.user.exception.code.UserErrorCode;
//import com.example.UMC9th.domain.user.repository.UserMissionRepository;
//import com.example.UMC9th.domain.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class MissionCommandServiceImpl implements MissionCommandService {
//
//    private final MissionRepository missionRepository;
//    private final UserRepository userRepository;
//    private final UserMissionRepository userMissionRepository;
//
//    @Override
//    @Transactional
//    public MissionResDTO.ChallengeDTO startChallenge(
//            Long missionId,
//            Long userId,
//            MissionReqDTO.ChallengeDTO dto
//    ) {
//        // 미션 조회
//        Mission mission = missionRepository.findById(missionId)
//                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));
//
//        // 유저 조회
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));
//
//        // UserMission 생성
//        UserMission userMission = MissionConverter.toUserMission(dto, user, mission);
//
//        // 저장
//        UserMission saved = userMissionRepository.save(userMission);
//
//        // 응답 DTO 반환
//        return MissionConverter.toChallengeDTO(saved);
//    }
//}