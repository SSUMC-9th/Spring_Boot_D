package com.example.UMC9th.domain.review.repository;


import com.example.UMC9th.domain.review.entity.Review;
import com.example.UMC9th.domain.store.entity.Store;
import com.example.UMC9th.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import static com.example.UMC9th.domain.store.entity.QStore.store;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    PageRequest pageRequest = PageRequest.of(page, 5);
    Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

    Page<Review> findAllByUser(User user, Pageable pageable);

}