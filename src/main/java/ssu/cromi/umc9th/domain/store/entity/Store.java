package ssu.cromi.umc9th.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ssu.cromi.umc9th.domain.store.enums.StoreCategory;
import ssu.cromi.umc9th.domain.store.enums.StoreStatus;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "store_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Store {
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

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(length = 500)
    private String description;

}
