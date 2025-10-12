package com.example.umc9th2.domain.User.entity;

import com.example.umc9th2.domain.User.entity.mapping.UserFood;
import com.example.umc9th2.domain.User.enums.FoodName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, length = 30)
    private FoodName name; // KOREAN, JAPANESE, CHINESE....

    /*
    연관관계
    user-food 다대다 관계 테이블의 중간 관계 테이블 userfood 테이블
     */
    @Builder.Default//기본 생성자가 있는 곳에는 다 빌더 디폴트를 적어놔야함
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<UserFood> userFoods = new ArrayList<>();
    //@BUilder를 사용할 경우에는
}
