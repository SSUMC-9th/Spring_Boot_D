package com.umc9th.peter.domain.store.controller;

import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.member.enums.AccountType;
import com.umc9th.peter.domain.member.repository.MemberRepository;
import com.umc9th.peter.domain.mission.entity.District;
import com.umc9th.peter.domain.mission.repository.DistrictRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreCategoryRepository storeCategoryRepository;

    @Autowired
    private DistrictRepository districtRepository;

    // TODO: 전체 필터에 대한 테스트 추가 필요

    @Test
    void getStores() throws Exception {

        Member owner = memberRepository.save(Member.builder().name("김사장").nickname("사장님").birthdate(LocalDate.of(2000, 1, 1)).address("김사장 주소").type(AccountType.OWNER).build());
        StoreCategory koreanFood = storeCategoryRepository.save(StoreCategory.builder().name("한식").build());
        StoreCategory drink = storeCategoryRepository.save(StoreCategory.builder().name("음료").build());
        District sangdoDistrict = districtRepository.save(District.builder().name("상도동").address("서울특별시 동작구 상도동").build());
        District heukseokDistrict = districtRepository.save(District.builder().name("흑석동").address("서울특별시 동작구 흑석동").build());
        Store dodamStore = storeRepository.save(Store.builder().name("도담식당").address("도담식당 주소").storeCategory(koreanFood).owner(owner).district(sangdoDistrict).build());
        Store sangdoStore = storeRepository.save(Store.builder().name("상도식당").address("상도식당 주소").storeCategory(koreanFood).owner(owner).district(sangdoDistrict).build());
        Store cafeSangdoStore = storeRepository.save(Store.builder().name("카페 상도점").address("카페 상도점 주소").storeCategory(drink).owner(owner).district(sangdoDistrict).build());
        Store cafeteriaStore = storeRepository.save(Store.builder().name("학생식당").address("학생식당 주소").storeCategory(koreanFood).owner(owner).district(heukseokDistrict).build());
        Store heukseokStore = storeRepository.save(Store.builder().name("흑석식당").address("흑석식당 주소").storeCategory(koreanFood).owner(owner).district(heukseokDistrict).build());
        Store cafeHeukseokStore = storeRepository.save(Store.builder().name("카페 흑석점").address("카페 흑석점 주소").storeCategory(drink).owner(owner).district(heukseokDistrict).build());

        mockMvc.perform(get("/stores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.content.length()").value(6));

        mockMvc.perform(get("/stores")
                        .param("districtId", String.valueOf(sangdoDistrict.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.content.length()").value(3));

        mockMvc.perform(get("/stores")
                        .param("keywords", "상도"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.content.length()").value(2));

        mockMvc.perform(get("/stores")
                        .param("keywords", "상도 흑석"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.content.length()").value(4));

        mockMvc.perform(get("/stores")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.content.length()").value(2))
                .andExpect(jsonPath("$.result.total").value(6));
    }
}