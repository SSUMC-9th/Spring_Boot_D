package com.example.umc9th2.domain.review.repository;

import com.example.umc9th2.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    // 리뷰 작성은 save() 메서드 자동 지원

    // 특정 유저가 작성한 리뷰 개수 -> 마이페이지 화면에 사용
    Long countByUser_UserId(Long userId);
    // 추후 userservice에서 사용자정보, 미션 합계, 리뷰 개수를 가지고 마이페이지 dto 구성
}
