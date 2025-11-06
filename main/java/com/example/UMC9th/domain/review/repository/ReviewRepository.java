package com.example.UMC9th.domain.review.repository;

import com.example.UMC9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository
        extends JpaRepository<Review, Long>, ReviewQueryDsl {

    Long countByUser_UserId(Long userId);
}