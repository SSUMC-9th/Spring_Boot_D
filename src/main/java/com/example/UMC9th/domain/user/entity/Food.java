package com.example.UMC9th.domain.user.entity;

import com.example.UMC9th.domain.user.entity.mapping.UserFood;
import com.example.UMC9th.domain.user.enums.FoodCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodId;

    @Column(name = "categoty")
    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;


    //연관관계
    @OneToMany(mappedBy = "food")
    private List<UserFood> userFoodList = new ArrayList<>();
}
