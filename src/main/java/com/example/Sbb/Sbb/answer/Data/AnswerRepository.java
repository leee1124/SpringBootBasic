package com.example.Sbb.Sbb.answer.Data;

import com.example.Sbb.Sbb.user.Data.SiteUserDTO;
import com.example.Sbb.Sbb.user.Data.SiteUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Integer>, AnswerCustomRepository {
    Optional<AnswerEntity> findById(Long id);
    Optional<List<AnswerEntity>> findByAuthor(SiteUserEntity siteUserEntity);
}
