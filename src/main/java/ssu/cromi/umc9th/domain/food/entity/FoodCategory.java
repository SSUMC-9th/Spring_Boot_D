package ssu.cromi.umc9th.domain.food.entity;

import jakarta.persistence.*;
import lombok.*;
import ssu.cromi.umc9th.domain.food.enums.CategoryTypes;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FoodCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private CategoryTypes name;

    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<UserFood> userFoods = new ArrayList<>();
}
