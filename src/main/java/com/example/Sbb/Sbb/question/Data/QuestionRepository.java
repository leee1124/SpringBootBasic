package com.example.Sbb.Sbb.question.Data;

import com.example.Sbb.Sbb.user.Data.SiteUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer>, QuestionCustomRepository {
    Optional<QuestionEntity> findById(Long id);

}
