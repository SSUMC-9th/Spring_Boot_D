package com.example.umc9th2.domain.User.entity;

import com.example.umc9th2.domain.User.entity.mapping.UserFood;
import com.example.umc9th2.domain.User.entity.mapping.UserMission;
import com.example.umc9th2.domain.User.entity.mapping.UserTerm;
import com.example.umc9th2.domain.User.enums.Gender;
import com.example.umc9th2.domain.User.enums.OauthProvider;
import com.example.umc9th2.global.auth.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
@Table(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
//BaseEntity 사용도 고려해볼 것
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;// PK(user_id)

    @Column(name = "name", nullable = false, length = 50)
    private String name;//user_name

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    private Gender gender;//male, female, none

    @Column(name = "birth", nullable = false)
    private LocalDate birth;//생년월일

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;//이메일

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)//소셜로그인
    @Column(name = "oauthprovider", nullable = false, length = 20)
    private OauthProvider oauthProvider;  // KAKAO, NAVER, GOOGLE

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;//전화번호(null)

    @Column(name = "delete_At")
    private LocalDateTime deletedAt = LocalDateTime.now();

    @CreatedDate
    @Column(name = "create_At", nullable = false)//생성일자
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "update_At", nullable = false)//수정일자
    private LocalDateTime updatedAt;

    @Column(name = "address", length = 255)
    private String address;        // 기본 주소

    @Column(name = "spec_address", length = 255)
    private String specAddress;    // 상세 주소

    /*
    연관관계
    mappedBy : 연관관계의 주인
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserMission> userMissions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserTerm> userTerms = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserFood> userFoods = new ArrayList<>();


}
