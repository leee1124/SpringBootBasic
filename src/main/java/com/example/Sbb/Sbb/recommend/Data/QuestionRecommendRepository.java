package com.example.Sbb.Sbb.recommend.Data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRecommendRepository extends JpaRepository<QuestionRecommendEntity, Long>, RecommendCustomRepository {
}
