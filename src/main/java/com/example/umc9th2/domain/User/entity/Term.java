package com.example.umc9th2.domain.User.entity;

import com.example.umc9th2.domain.User.enums.TermName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "term")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TermName name; // SERVICE, PRIVACY, LOCATION, MARKETING
}
