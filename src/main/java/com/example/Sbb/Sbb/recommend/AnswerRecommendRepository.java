package com.example.Sbb.Sbb.recommend;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRecommendRepository extends JpaRepository<AnswerRecommendEntity, Long>, RecommendCustomRepository {
}
