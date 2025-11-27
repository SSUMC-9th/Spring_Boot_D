package com.example.umc9th2.domain.Food.repository;

import com.example.umc9th2.domain.Food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
