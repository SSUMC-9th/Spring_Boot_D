package com.example.UMC9th.domain.mission.service.query;

import com.example.UMC9th.domain.mission.converter.MissionConverter;
import com.example.UMC9th.domain.mission.dto.MissionResDTO;
import com.example.UMC9th.domain.mission.entity.Mission;
import com.example.UMC9th.domain.mission.enums.MissonState;
import com.example.UMC9th.domain.mission.repository.MissionRepository;
import com.example.UMC9th.domain.store.entity.Store;
import com.example.UMC9th.domain.user.entity.User;
import com.example.UMC9th.domain.store.exception.StoreException;
import com.example.UMC9th.domain.store.exception.code.StoreErrorCode;
import com.example.UMC9th.domain.user.entity.mapping.UserMission;
import com.example.UMC9th.domain.user.exception.UserException;
import com.example.UMC9th.domain.user.exception.code.UserErrorCode;
import com.example.UMC9th.domain.user.repository.UserMissionRepository;
import com.example.UMC9th.domain.user.repository.UserRepository;
import com.example.UMC9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService{
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;


    @Override
    public MissionResDTO.StoreMissionPreviewListDTO getStoreMissions(
            Long storeId,
            Integer page
    ) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 5);   // pageSize는 취향대로
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);

        return MissionConverter.toStoreMissionPreviewListDTO(result);
    }

    @Override
    public MissionResDTO.MyMissionPreviewListDTO getMyMissions(
            Long userId,
            Integer page
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<UserMission> result = userMissionRepository
                .findAllByUserAndMissionState(user, MissonState.INPROGRESS, pageRequest);

        return MissionConverter.toMyMissionPreviewListDTO(result);
    }

}
