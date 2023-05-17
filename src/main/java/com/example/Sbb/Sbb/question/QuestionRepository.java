package com.example.Sbb.Sbb.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    QuestionEntity findBySubject(String subject);
    QuestionEntity findBySubjectAndContent(String subject, String content);
    List<QuestionEntity> findBySubjectLike(String subject);
    Page<QuestionEntity> findAll(Pageable pageable);
    @Query("SELECT DISTINCT q FROM QuestionEntity q JOIN FETCH q.answerList a WHERE a.id = :answerId")
    QuestionEntity findQuestionWithAnswersById(@Param("answerId") Integer answerId);

}
