package com.example.umc9th2.domain.User.entity;

import com.example.umc9th2.domain.User.entity.mapping.UserFood;
import com.example.umc9th2.domain.User.entity.mapping.UserMission;
import com.example.umc9th2.domain.User.entity.mapping.UserTerm;
import com.example.umc9th2.domain.User.enums.Gender;
import com.example.umc9th2.domain.User.enums.OauthProvider;
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
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

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

    @Enumerated(EnumType.STRING)//소셜로그인
    @Column(name = "oauthprovider", nullable = false, length = 20)
    private OauthProvider oauthProvider;  // KAKAO, NAVER, GOOGLE

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;//전화번호(null)

    @Column(name = "delete_At", nullable = false)//삭제일자(null)
    private LocalDateTime deletedAt = LocalDateTime.now();

    @CreatedDate
    @Column(name = "create_At", nullable = false)//생성일자
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "update_At", nullable = false)//수정일자
    private LocalDateTime updatedAt;

    /*
    연관관계
    mappedBy : 연관관계의 주인
     */

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissions = new ArrayList<>();//유저미션

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTerm> userTerms = new ArrayList<>();//유저약관

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserFood> userFoods = new ArrayList<>();//유저선호음식

}
