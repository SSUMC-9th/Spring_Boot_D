package com.example.umc9th2.domain.User.entity.mapping;

import com.example.umc9th2.domain.User.entity.Food;
import com.example.umc9th2.domain.User.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_food")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 유저선호음식 id (PK)

    //연관관계
    // 한 회원이 여러 선호음식을 가질 수 있음
    @ManyToOne(fetch = FetchType.LAZY)//지연로딩
    @JoinColumn(name = "user_id", nullable = false)//FK
    private User user;  // 유저 ID

    // 하나의 음식이 여러 회원에게 선호될 수 있음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)//FK
    private Food food;  // 음식 ID
}
