package com.example.UMC9th.domain.review.repository;


import com.example.UMC9th.domain.review.entity.Review;
import com.example.UMC9th.domain.store.entity.Store;
import com.example.UMC9th.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}