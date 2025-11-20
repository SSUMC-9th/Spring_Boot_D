package com.example.UMC9th.domain.store.entity;

import com.example.UMC9th.domain.mission.entity.Mission;
import com.example.UMC9th.domain.review.entity.Review;
import com.example.UMC9th.domain.store.enums.Address;
import com.example.UMC9th.domain.store.enums.StoreInfo;
import com.example.UMC9th.domain.user.entity.mapping.UserFood;
import com.example.UMC9th.domain.user.entity.mapping.UserTerm;
import com.example.UMC9th.domain.user.enums.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "store_info", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private StoreInfo storeInfo;//open,closed

    @Column(name = "store_category", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private FoodCategory storeCategory;//open,closed

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Mission> missionList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Review> reviewList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

}
