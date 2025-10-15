package com.umc9th.peter.domain.review.repository;

import com.umc9th.peter.domain.review.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Modifying
    @Query("DELETE FROM Answer a WHERE a.id NOT IN (SELECT DISTINCT r.answer.id FROM Review r)")
    void deleteOrphans();

}
