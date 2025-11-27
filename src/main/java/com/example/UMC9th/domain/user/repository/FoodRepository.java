package com.example.UMC9th.domain.user.repository;

import com.example.UMC9th.domain.user.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}