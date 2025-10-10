package com.umc9th.peter.domain.review.repository;

import com.umc9th.peter.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Modifying
    @Query("DELETE FROM Review r WHERE r.author.id = :authorId")
    void deleteByAuthorId(@Param("authorId") long authorId);

}
