package com.example.umc9th2.domain.User.repository;

import com.example.umc9th2.domain.User.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {
}

