package com.example.Sbb.Sbb.answer.Data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Integer> {
    Optional<AnswerEntity> findById(Long id);
}
