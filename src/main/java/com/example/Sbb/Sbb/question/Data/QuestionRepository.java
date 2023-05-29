package com.example.Sbb.Sbb.question.Data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer>, QuestionCustomRepository {
    Optional<QuestionEntity> findById(Long id);
}
