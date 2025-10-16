package ssu.cromi.umc9th.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ssu.cromi.umc9th.domain.food.entity.UserFood;
import ssu.cromi.umc9th.domain.mission.entity.UserMission;
import ssu.cromi.umc9th.domain.mission.entity.UserMissionLocation;
import ssu.cromi.umc9th.domain.review.entity.ReviewComment;
import ssu.cromi.umc9th.domain.review.entity.ReviewPictures;
import ssu.cromi.umc9th.domain.review.entity.UserReview;
import ssu.cromi.umc9th.domain.user.enums.Gender;
import ssu.cromi.umc9th.domain.user.enums.UserStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 255)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @Column(length = 255)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private UserStatus status = UserStatus.ACTIVE;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    @Builder.Default
    private Long userPoint = 0L;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<UserAgreement> agreements = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<UserFood> userFoods = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<UserMission> userMissions = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<UserMissionLocation> nextMissionLocations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<UserReview> userReviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<ReviewComment> reviewComments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<ReviewPictures> reviewPictures = new ArrayList<>();
}
