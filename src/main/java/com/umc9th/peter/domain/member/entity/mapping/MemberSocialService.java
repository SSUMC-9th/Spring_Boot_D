package com.umc9th.peter.domain.member.entity.mapping;

import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.member.entity.SocialService;
import com.umc9th.peter.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(
        name = "member_social_service",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"service_id", "social_id"})
        }
)
public class MemberSocialService extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private SocialService service;

    @Column(name = "social_id", nullable = false)
    private String socialId;

}
