package com.umc9th.peter.domain.mission.controller;

import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.member.enums.AccountType;
import com.umc9th.peter.domain.member.repository.MemberRepository;
import com.umc9th.peter.domain.mission.entity.District;
import com.umc9th.peter.domain.mission.entity.Mission;
import com.umc9th.peter.domain.mission.exception.code.MissionErrorCode;
import com.umc9th.peter.domain.mission.repository.DistrictRepository;
import com.umc9th.peter.domain.mission.repository.MissionRepository;
import com.umc9th.peter.domain.store.entity.Store;
import com.umc9th.peter.domain.store.entity.StoreCategory;
import com.umc9th.peter.domain.store.repository.StoreCategoryRepository;
import com.umc9th.peter.domain.store.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MissionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StoreCategoryRepository storeCategoryRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Test
    void acceptMission() throws Exception {
        Member author = memberRepository.save(Member.builder().name("김숭실").nickname("SS Kim").birthdate(LocalDate.of(2000, 1, 1)).address("서울특별시 동작구 상도로 369").addressDetail("세부 주소").type(AccountType.CUSTOMER).build());
        Member owner = memberRepository.save(Member.builder().name("숭실대학교").nickname("숭실대").birthdate(LocalDate.of(1897, 10, 10)).address("서울특별시 동작구 상도로 369").type(AccountType.OWNER).build());
        StoreCategory koreanFood = storeCategoryRepository.save(StoreCategory.builder().name("한식").build());
        District sangdoDistrict = districtRepository.save(District.builder().name("상도동").address("서울특별시 동작구 상도동").build());
        Store dodamStore = storeRepository.save(Store.builder().name("도담식당").address("서울시 동작구 상도로 369").storeCategory(koreanFood).owner(owner).district(sangdoDistrict).build());
        Mission normalMission = missionRepository.save(Mission.builder().store(dodamStore).content("10,000원 이상 식사 시 500 포인트").beginAt(LocalDateTime.now().minusDays(1)).endAt(LocalDateTime.now().plusDays(1)).build());
        Mission closedMission = missionRepository.save(Mission.builder().store(dodamStore).content("20,000원 이상 식사 시 1000 포인트").beginAt(LocalDateTime.now().minusDays(10)).endAt(LocalDateTime.now().minusDays(1)).build());
        Mission notOpenedMission = missionRepository.save(Mission.builder().store(dodamStore).content("20,000원 이상 식사 시 1000 포인트").beginAt(LocalDateTime.now().plusDays(1)).endAt(LocalDateTime.now().plusDays(10)).build());

        mockMvc.perform(post("/missions/{missionId}/accept", normalMission.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").isNumber());

        mockMvc.perform(post("/missions/{missionId}/accept", normalMission.getId()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(MissionErrorCode.ALREADY_ACCEPTED.getMessage()));

        mockMvc.perform(post("/missions/{missionId}/accept", closedMission.getId()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(MissionErrorCode.CLOSED.getMessage()));

        mockMvc.perform(post("/missions/{missionId}/accept", notOpenedMission.getId()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(MissionErrorCode.NOT_OPENED.getMessage()));

        mockMvc.perform(post("/missions/{missionId}/accept", -1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MissionErrorCode.NOT_FOUND.getMessage()));
    }

}
