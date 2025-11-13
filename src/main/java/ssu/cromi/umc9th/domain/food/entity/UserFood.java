package ssu.cromi.umc9th.domain.food.entity;

import jakarta.persistence.*;
import lombok.*;
import ssu.cromi.umc9th.domain.user.entity.User;
import ssu.cromi.umc9th.global.entity.BaseEntity;

@Entity
@Table(name = "user_food")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserFood extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_category_id", nullable = false)
    private FoodCategory foodCategory;
}
