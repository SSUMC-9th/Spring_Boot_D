package com.example.umc9th2.domain.User.entity.mapping;

import com.example.umc9th2.domain.User.entity.Term;
import com.example.umc9th2.domain.User.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_term")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberTermId;

    /*
    연관관계
    user-term 다대다 관계 테이블의 중간 테이블 userterm
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)//FK
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id", nullable = false)//FK
    private Term term;
}
