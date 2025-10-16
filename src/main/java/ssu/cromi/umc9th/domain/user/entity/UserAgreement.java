package ssu.cromi.umc9th.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_agreement")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Builder.Default
    private Boolean requiredConsent = false;

    @Column(nullable = false)
    @Builder.Default
    private Boolean termsOfService = false;

    @Column(nullable = false)
    @Builder.Default
    private Boolean marketingAgree = false;
}
