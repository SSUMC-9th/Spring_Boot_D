package ssu.cromi.umc9th.domain.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssu.cromi.umc9th.domain.food.entity.UserFood;

public interface UserFoodRepository extends JpaRepository<UserFood,Long> {
}
