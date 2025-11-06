package com.example.UMC9th.domain.store.entity;

import com.example.UMC9th.domain.store.enums.Address;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_name", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Address locationName;

    @Builder.Default
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Store> storeList = new ArrayList<>();
}
