package com.example.Sbb.Sbb.question;

import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer>, QuestionCustomRepository {
    Optional<QuestionEntity> findById(Long id);
}
