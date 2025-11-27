package ssu.cromi.umc9th.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssu.cromi.umc9th.domain.mission.converter.MissionConverter;
import ssu.cromi.umc9th.domain.mission.dto.MissionReqDTO;
import ssu.cromi.umc9th.domain.mission.dto.MissionResDTO;
import ssu.cromi.umc9th.domain.mission.entity.Mission;
import ssu.cromi.umc9th.domain.mission.entity.UserMission;
import ssu.cromi.umc9th.domain.mission.enums.UserMissionStatus;
import ssu.cromi.umc9th.domain.mission.exception.MissionException.MissionException;
import ssu.cromi.umc9th.domain.mission.exception.code.MissionErrorCode;
import ssu.cromi.umc9th.domain.mission.repository.MissionRepository;
import ssu.cromi.umc9th.domain.mission.repository.UserMissionRepository;
import ssu.cromi.umc9th.domain.store.entity.Store;
import ssu.cromi.umc9th.domain.store.exception.StoreException.StoreException;
import ssu.cromi.umc9th.domain.store.exception.code.StoreErrorCode;
import ssu.cromi.umc9th.domain.store.repository.StoreRepository;
import ssu.cromi.umc9th.domain.user.entity.User;
import ssu.cromi.umc9th.domain.user.exception.UserException.UserException;
import ssu.cromi.umc9th.domain.user.exception.code.UserErrorCode;
import ssu.cromi.umc9th.domain.user.repository.UserRepository;
import ssu.cromi.umc9th.global.apiPayload.code.GeneralErrorCode;
import ssu.cromi.umc9th.global.apiPayload.exception.GeneralException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public MissionResDTO.ChallengeDTO challengeMission(Long missionId, MissionReqDTO.ChallengeDTO dto) {
        // 사용자 존재 확인
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        // 미션 존재 확인
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        // 중복 도전 확인
        if (userMissionRepository.existsActiveUserMission(dto.userId(), missionId)) {
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGED);
        }

        // 미션 만료 확인
        if (mission.getDueDate().isBefore(LocalDate.now())) {
            throw new MissionException(MissionErrorCode.MISSION_EXPIRED);
        }

        // UserMission 생성 및 저장
        UserMission userMission = MissionConverter.toUserMission(dto, user, mission);
        userMissionRepository.save(userMission);

        // 응답 DTO 반환
        return MissionConverter.toChallengeDTO(userMission);
    }

    public MissionResDTO.StoreMissionListDTO getStoreMissions(String storeName, Integer page) {
        // 페이지 유효성 검증 (1 이상)
        if (page < 1) {
            throw new GeneralException(GeneralErrorCode.INVALID_PAGE);
        }

        // 가게 존재 확인
        Store store = storeRepository.findByStoreName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 페이징 처리 (page는 1부터 시작하므로 -1, 한 페이지에 10개씩)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);

        return MissionConverter.toStoreMissionListDTO(result);
    }

    public MissionResDTO.UserMissionListDTO getUserInProgressMissions(Long userId, Integer page) {
        // 페이지 유효성 검증 (1 이상)
        if (page < 1) {
            throw new GeneralException(GeneralErrorCode.INVALID_PAGE);
        }

        // 사용자 존재 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        // 페이징 처리 (page는 1부터 시작하므로 -1, 한 페이지에 10개씩)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<UserMission> result = userMissionRepository.findAllByUserAndStatus(
                user,
                UserMissionStatus.IN_PROGRESS,
                pageRequest
        );

        return MissionConverter.toUserMissionListDTO(result);
    }
}
