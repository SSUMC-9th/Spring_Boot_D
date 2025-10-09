package com.umc9th.peter.domain.member.entity.mapping;

import com.umc9th.peter.domain.member.entity.FoodCategory;
import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_food_category")
public class MemberFoodCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_category_id", nullable = false)
    private FoodCategory foodCategory;

}
