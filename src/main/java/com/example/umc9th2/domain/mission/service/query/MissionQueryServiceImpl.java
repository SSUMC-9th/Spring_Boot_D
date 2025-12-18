package com.example.umc9th2.domain.mission.service.query;

import com.example.umc9th2.domain.User.entity.mapping.UserMission;
import com.example.umc9th2.domain.User.repository.UserMissionRepository;
import com.example.umc9th2.domain.mission.converter.MissionConverter;
import com.example.umc9th2.domain.mission.dto.MissionResponseDto;
import com.example.umc9th2.domain.mission.entity.Mission;
import com.example.umc9th2.domain.mission.repository.MissionRepository;
import com.example.umc9th2.domain.store.entity.Store;
import com.example.umc9th2.domain.store.repository.StoreRepository;
import com.example.umc9th2.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th2.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final UserMissionRepository userMissionRepository;

    //특정 가게 미션 목록
    @Override
    public MissionResponseDto.MissionListDTO getStoreMissions(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        Page<Mission> missions = missionRepository.findAllByStore(store, pageRequest);

        return MissionConverter.toListDTO(missions);
    }

    //내가 진행중인 미션 목록
    //내가 한 미션 -> usermission에서 조회한 다음 그 목록에서 미션 엔티티만 추출해서 미션 리스트로 변환
    @Override
    public MissionResponseDto.MissionListDTO getMyMissions(Long userId, Integer page) {

        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Mission> missions =
                userMissionRepository.findAllByUser_UserId(userId, pageable)
                        .map(UserMission::getMission);//미션만 추출

        return MissionConverter.toListDTO(missions);
    }
}
