package com.umc9th.peter.domain.member.repository;

import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.member.entity.mapping.MemberMission;
import com.umc9th.peter.domain.member.enums.AccountType;
import com.umc9th.peter.domain.member.enums.MissionStatus;
import com.umc9th.peter.domain.mission.entity.District;
import com.umc9th.peter.domain.mission.entity.Mission;
import com.umc9th.peter.domain.mission.repository.DistrictRepository;
import com.umc9th.peter.domain.mission.repository.MissionRepository;
import com.umc9th.peter.domain.store.entity.Store;
import com.umc9th.peter.domain.store.entity.StoreCategory;
import com.umc9th.peter.domain.store.repository.StoreCategoryRepository;
import com.umc9th.peter.domain.store.repository.StoreRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

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

    @Autowired
    private MemberMissionRepository memberMissionRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findByIdWithMemberMissionList() {
        Member member1 = Member.builder().name("김숭실").nickname("SS Kim").birthdate(LocalDate.of(2000, 1, 1)).address("서울특별시 동작구 상도로 369").addressDetail("세부 주소1").type(AccountType.CUSTOMER).build();
        Member member2 = Member.builder().name("이숭실").nickname("SS Lee").birthdate(LocalDate.of(2000, 1, 1)).address("서울특별시 동작구 상도로 369").addressDetail("세부 주소2").type(AccountType.CUSTOMER).build();
        Member owner = Member.builder().name("숭실대학교").nickname("숭실대").birthdate(LocalDate.of(1897, 10, 10)).address("서울특별시 동작구 상도로 369").type(AccountType.OWNER).build();
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(owner);

        StoreCategory koreanFood = StoreCategory.builder().name("한식").build();
        storeCategoryRepository.save(koreanFood);

        Store dodamStore = Store.builder().name("도담식당").storeCategory(koreanFood).owner(owner).build();
        storeRepository.save(dodamStore);

        District sangdoDistrict = District.builder().name("상도동").address("서울특별시 동작구 상도동").build();
        districtRepository.save(sangdoDistrict);

        LocalDateTime now = LocalDateTime.now();
        Mission dodamMission1 = Mission.builder().store(dodamStore).content("6,000원 이상의 식사").beginAt(now.minusDays(1)).endAt(now.plusDays(1)).build();
        Mission dodamMission2 = Mission.builder().store(dodamStore).content("12,000원 이상의 식사").beginAt(now.minusDays(10)).endAt(now.minusDays(1)).build();
        missionRepository.save(dodamMission1);
        missionRepository.save(dodamMission2);

        MemberMission memberMission1 = MemberMission.builder().status(MissionStatus.ACCEPTED).member(member1).mission(dodamMission1).build();
        MemberMission memberMission2 = MemberMission.builder().status(MissionStatus.COMPLETED).member(member1).mission(dodamMission2).build();
        MemberMission memberMission3 = MemberMission.builder().status(MissionStatus.ACCEPTED).member(member2).mission(dodamMission2).build();
        memberMissionRepository.save(memberMission1);
        memberMissionRepository.save(memberMission2);
        memberMissionRepository.save(memberMission3);

        entityManager.flush();
        entityManager.clear();

        Member member = memberRepository.findByIdWithMemberMissionList(member1.getId());
        entityManager.detach(member);

        // Detach 되었으므로 fetch join 되지 않았다면 LazyInitializationException 발생
        assertDoesNotThrow(() -> member.getMemberMissionList().getFirst());
    }
}