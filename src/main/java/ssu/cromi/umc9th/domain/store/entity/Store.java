package ssu.cromi.umc9th.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import ssu.cromi.umc9th.domain.store.enums.StoreCategory;
import ssu.cromi.umc9th.domain.store.enums.StoreStatus;
import ssu.cromi.umc9th.global.entity.BaseEntity;

import java.time.LocalTime;

@Entity
@Table(name = "store_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String storeName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private StoreCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private StoreStatus status = StoreStatus.ACTIVE;

    @Column(nullable = false)
    private LocalTime openTime;

    @Column(nullable = false)
    private LocalTime closeTime;

    @Column(name = "scores")
    private Long scores;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(length = 500)
    private String description;

}
