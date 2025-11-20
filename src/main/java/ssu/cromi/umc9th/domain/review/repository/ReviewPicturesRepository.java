package ssu.cromi.umc9th.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssu.cromi.umc9th.domain.review.entity.ReviewPictures;

public interface ReviewPicturesRepository extends JpaRepository<ReviewPictures, Long> {
}
