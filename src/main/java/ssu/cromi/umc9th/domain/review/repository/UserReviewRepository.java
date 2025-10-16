package ssu.cromi.umc9th.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ssu.cromi.umc9th.domain.review.entity.UserReview;
import ssu.cromi.umc9th.domain.store.entity.Store;
import ssu.cromi.umc9th.domain.user.entity.User;

import java.time.LocalDateTime;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
    @Modifying
    @Query("INSERT INTO UserReview (user, store, score, reviewText, createdAt, updatedAt) " + "VALUES (:user, :store, :score, :reviewText, :createdAt, :updatedAt)")
    void insertReview(@Param("user") User user,
                      @Param("store") Store store,
                      @Param("score") Integer score,
                      @Param("reviewText") String reviewText,
                      @Param("createdAt") LocalDateTime createdAt,
                      @Param("updatedAt") LocalDateTime updatedAt);
}
