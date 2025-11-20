package com.example.UMC9th.domain.user.repository;

import com.example.UMC9th.domain.user.entity.Food;
import com.example.UMC9th.domain.user.entity.User;
import com.example.UMC9th.domain.user.entity.mapping.UserFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFoodRepository extends JpaRepository<UserFood, Long> {
}
