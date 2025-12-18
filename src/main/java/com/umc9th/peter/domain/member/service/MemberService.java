package com.umc9th.peter.domain.member.service;

import com.umc9th.peter.domain.member.converter.MemberConverter;
import com.umc9th.peter.domain.member.dto.MemberRequest;
import com.umc9th.peter.domain.member.dto.MemberResponse;
import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.member.entity.mapping.MemberFoodCategory;
import com.umc9th.peter.domain.member.entity.mapping.MemberMission;
import com.umc9th.peter.domain.member.enums.MissionStatus;
import com.umc9th.peter.domain.member.exception.FoodException;
import com.umc9th.peter.domain.member.exception.code.FoodErrorCode;
import com.umc9th.peter.domain.member.repository.*;
import com.umc9th.peter.domain.review.repository.AnswerRepository;
import com.umc9th.peter.domain.review.repository.ReviewRepository;
import com.umc9th.peter.domain.store.repository.StoreRepository;
import com.umc9th.peter.global.auth.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberTermRepository memberTermRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberFoodCategoryRepository memberFoodCategoryRepository;
    private final MemberSocialServiceRepository memberSocialServiceRepository;

    private final ReviewRepository reviewRepository;
    private final AnswerRepository answerRepository;
    private final StoreRepository storeRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResponse.joinDto signUp(
            MemberRequest.joinDto dto
    ) {
        String salt = passwordEncoder.encode(dto.password());
        Member member = MemberRequest.joinDto.toEntity(dto, salt, Role.ROLE_USER);
        memberRepository.save(member);

        if (!dto.foodCategory().isEmpty()) {
            List<MemberFoodCategory> memberFoodCategoryList = dto.foodCategory().stream()
                    // TODO: DTO에서 검증을 위해 repository를 조회하며 불필요하게 이중으로 접근하게 됨
                    //       서비스 계층에서도 매핑을 위해 조회가 필요 (트랜잭션 내부에서 존재하는지 재검증 필요)
                    .map(id -> foodCategoryRepository.findById(id)
                            .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
                    .map(foodCategory -> MemberFoodCategory.builder()
                            .member(member)
                            .foodCategory(foodCategory)
                            .build()
                    )
                    .toList();
            memberFoodCategoryRepository.saveAll(memberFoodCategoryList);
        }

        return MemberResponse.joinDto.fromEntity(member);
    }

    @Transactional
    public void deleteAccountByMemberId(long memberId) {
        memberTermRepository.deleteByMemberId(memberId);
        memberMissionRepository.deleteByMemberId(memberId);
        memberFoodCategoryRepository.deleteByMemberId(memberId);
        memberSocialServiceRepository.deleteByMemberId(memberId);

        reviewRepository.deleteByAuthorId(memberId);
        answerRepository.deleteOrphans();
        storeRepository.clearOwnerByOwnerId(memberId);  // 점주가 탈퇴하더라도 가게 정보는 유지

        memberRepository.deleteById(memberId);
    }

    public MemberResponse.MissionListDto getMissions(
            Long memberId,
            MissionStatus status,
            Integer page,
            Integer limit
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, limit);

        Page<MemberMission> memberMissions = (status == null ?
                memberMissionRepository.findByMemberId(memberId, pageRequest) :
                memberMissionRepository.findByMemberIdAndStatus(memberId, status, pageRequest));

        return MemberConverter.toMissionListDto(memberMissions);
    }
}
