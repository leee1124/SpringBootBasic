package com.example.Sbb.Sbb.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    Page<QuestionEntity> findAll(Pageable pageable);
}
