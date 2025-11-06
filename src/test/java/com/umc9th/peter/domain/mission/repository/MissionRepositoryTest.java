package com.umc9th.peter.domain.mission.repository;

import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.member.enums.AccountType;
import com.umc9th.peter.domain.member.repository.MemberRepository;
import com.umc9th.peter.domain.mission.entity.District;
import com.umc9th.peter.domain.mission.entity.Mission;
import com.umc9th.peter.domain.store.entity.Store;
import com.umc9th.peter.domain.store.entity.StoreCategory;
import com.umc9th.peter.domain.store.repository.StoreCategoryRepository;
import com.umc9th.peter.domain.store.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class MissionRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreCategoryRepository storeCategoryRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Test
    void findAvailableMissionsByDistrictId() {
        Member member = Member.builder().name("김숭실").nickname("SS Kim").birthdate(LocalDate.of(2000, 1, 1)).address("서울특별시 동작구 상도로 369").addressDetail("세부 주소").type(AccountType.CUSTOMER).build();
        Member owner = Member.builder().name("숭실대학교").nickname("숭실대").birthdate(LocalDate.of(1897, 10, 10)).address("서울특별시 동작구 상도로 369").type(AccountType.OWNER).build();
        memberRepository.save(member);
        memberRepository.save(owner);

        StoreCategory koreanFood = StoreCategory.builder().name("한식").build();
        storeCategoryRepository.save(koreanFood);

        District sangdoDistrict = District.builder().name("상도동").address("서울특별시 동작구 상도동").build();
        districtRepository.save(sangdoDistrict);

        Store dodamStore = Store.builder().name("도담식당").address("서울시 동작구 상도로 369").storeCategory(koreanFood).owner(owner).district(sangdoDistrict).build();
        storeRepository.save(dodamStore);

        LocalDateTime now = LocalDateTime.now();
        Mission dodamMission1 = Mission.builder().store(dodamStore).content("6,000원 이상의 식사").beginAt(now.minusDays(1)).endAt(now.plusDays(1)).build();
        Mission dodamMission2 = Mission.builder().store(dodamStore).content("12,000원 이상의 식사").beginAt(now.minusDays(10)).endAt(now.minusDays(1)).build();
        Mission dodamMission3 = Mission.builder().store(dodamStore).content("24,000원 이상의 식사").beginAt(now.plusDays(1)).endAt(now.plusDays(10)).build();
        missionRepository.save(dodamMission1);
        missionRepository.save(dodamMission2);
        missionRepository.save(dodamMission3);

        Page<Mission> availableMissions = missionRepository.findAvailableMissionsByDistrictId(member.getId(), sangdoDistrict.getId(), now, PageRequest.of(0, 10));
        availableMissions.forEach(mission -> {
            assertThat(mission.getMissionDistrictList().stream()
                    .filter(missionDistrict -> missionDistrict.getDistrict().equals(sangdoDistrict))
                    .count()).isNotZero();
            assertThat(mission.getBeginAt().isBefore(now) && mission.getEndAt().isAfter(now)).isTrue();
        });

    }
}