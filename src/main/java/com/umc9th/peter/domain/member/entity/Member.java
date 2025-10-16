package com.umc9th.peter.domain.member.entity;

import com.umc9th.peter.domain.member.entity.mapping.MemberFoodCategory;
import com.umc9th.peter.domain.member.entity.mapping.MemberMission;
import com.umc9th.peter.domain.member.entity.mapping.MemberSocialService;
import com.umc9th.peter.domain.member.enums.AccountStatus;
import com.umc9th.peter.domain.member.enums.AccountType;
import com.umc9th.peter.domain.member.enums.Gender;
import com.umc9th.peter.domain.review.entity.Review;
import com.umc9th.peter.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 16, nullable = false)
    private String name;

    @Column(name = "nickname", length = 16, nullable = false)
    private String nickname;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "address_detail")
    private String addressDetail;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AccountStatus status = AccountStatus.ACTIVE;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<Review> memberReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<MemberFoodCategory> memberFoodCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<MemberSocialService> memberSocialServiceList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();

}
