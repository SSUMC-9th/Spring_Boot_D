package com.umc9th.peter.domain.member.service;

import com.umc9th.peter.domain.member.repository.*;
import com.umc9th.peter.domain.review.repository.AnswerRepository;
import com.umc9th.peter.domain.review.repository.ReviewRepository;
import com.umc9th.peter.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
