package com.example.UMC9th.domain.user.entity;

import com.example.UMC9th.domain.user.entity.mapping.UserFood;
import com.example.UMC9th.domain.user.enums.TermName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termId;

    @Column(name = "term_name")
    @Enumerated(EnumType.STRING)
    private TermName termName;


    //연관관계
    @OneToMany(mappedBy = "term")
    private List<UserFood> userFoodList = new ArrayList<>();
}
