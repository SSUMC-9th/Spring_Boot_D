package com.example.UMC9th.domain.user.entity.mapping;

import com.example.UMC9th.domain.user.entity.User;
import com.example.UMC9th.domain.user.entity.Food;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "user_food")
public class UserFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)//cascade remove적용
    @JoinColumn(name = "food_id")
    private Food food;
}
