package ssu.cromi.umc9th.domain.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssu.cromi.umc9th.domain.food.entity.FoodCategory;

public interface FoodRepository extends JpaRepository<FoodCategory, Long> {
}
