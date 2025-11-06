package com.example.UMC9th.domain.user.entity;

import com.example.UMC9th.domain.user.entity.mapping.UserFood;
import com.example.UMC9th.domain.user.entity.mapping.UserMission;
import com.example.UMC9th.domain.user.entity.mapping.UserTerm;
import com.example.UMC9th.domain.user.enums.Gender;
import com.example.UMC9th.domain.store.enums.Address;
import com.example.UMC9th.global.auth.enums.SocialType;
import com.example.UMC9th.global.auth.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity{

    //사용자 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    //이름
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    //성별
    @Column(name = "gender", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    //생년원일
    @Column(name ="birth", nullable = false, length = 10)
    private String birth;
    //private LocalDate birth;

    //주소
    @Column(name = "address", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Address address;

    //이메일
    @Column(name = "email", nullable = false, length = 20, unique = true)
    private String email;

    //전화번호
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    //미션 포인트
    @Column(name = "point", nullable = false)
    private Integer point;

    //소셜 UID
    @Column(name = "social_uid", nullable = false)
    private String socialUid;

    //소셜타입
    @Column(name = "social_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;



    //연관관계
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserFood> userFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserTerm> userTermList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserMission> missionList = new ArrayList<>();

    //연관관계 공부 한 번 더하기
}