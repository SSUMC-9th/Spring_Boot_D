package com.umc9th.peter.domain.review.controller;

import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.member.enums.AccountType;
import com.umc9th.peter.domain.member.repository.MemberRepository;
import com.umc9th.peter.domain.mission.entity.District;
import com.umc9th.peter.domain.mission.repository.DistrictRepository;
import com.umc9th.peter.domain.review.entity.Review;
import com.umc9th.peter.domain.review.repository.ReviewRepository;
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
class ReviewControllerTest {

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

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void getReviews() throws Exception {

        Member member = memberRepository.save(Member.builder().name("김숭실").nickname("SS Kim").birthdate(LocalDate.of(2000, 1, 1)).address("서울특별시 동작구 상도로 369").addressDetail("세부 주소").type(AccountType.CUSTOMER).build());
        Member ssu = memberRepository.save(Member.builder().name("숭실대학교").nickname("숭실대").birthdate(LocalDate.of(1897, 10, 10)).address("서울특별시 동작구 상도로 369").type(AccountType.OWNER).build());
        Member cau = memberRepository.save(Member.builder().name("숭실대학교").nickname("숭실대").birthdate(LocalDate.of(1897, 10, 10)).address("서울특별시 동작구 상도로 369").type(AccountType.OWNER).build());
        StoreCategory koreanFood = storeCategoryRepository.save(StoreCategory.builder().name("한식").build());
        District sangdoDistrict = districtRepository.save(District.builder().name("상도동").address("서울특별시 동작구 상도동").build());
        District heukseokDistrict = districtRepository.save(District.builder().name("흑석동").address("서울특별시 동작구 흑석동").build());
        Store dodamStore = storeRepository.save(Store.builder().name("도담식당").address("서울시 동작구 상도로 369").storeCategory(koreanFood).owner(ssu).district(sangdoDistrict).build());
        Store cauStore = storeRepository.save(Store.builder().name("학생식당").address("서울시 동작구 흑석로 84").storeCategory(koreanFood).owner(cau).district(heukseokDistrict).build());
        Review dodamReview1 = reviewRepository.save(Review.builder().author(member).store(dodamStore).star(5).content("5점 리뷰 1").build());
        Review dodamReview2 = reviewRepository.save(Review.builder().author(member).store(dodamStore).star(4).content("4점 리뷰 1").build());
        Review cauReview1 = reviewRepository.save(Review.builder().author(member).store(cauStore).star(5).content("5점 리뷰 2").build());
        Review cauReview2 = reviewRepository.save(Review.builder().author(member).store(cauStore).star(4).content("4점 리뷰 2").build());

        // 현재 인증 기능이 구현되어 있지 않아서 멤버별 필터링은 불가한 상태
        // 모든 멤버의 리뷰를 조건에 따라 조회

        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.length()").value(4));

        mockMvc.perform(get("/reviews")
                        .param("storeId", String.valueOf(dodamStore.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.length()").value(2));

        mockMvc.perform(get("/reviews")
                        .param("star", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.length()").value(2));

        mockMvc.perform(get("/reviews")
                        .param("storeId", String.valueOf(cauStore.getId()))
                        .param("star", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.length()").value(1));
    }

}
