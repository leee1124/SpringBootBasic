package com.example.Sbb.Sbb.recommender;

import com.example.Sbb.Sbb.question.QuestionEntity;
import com.example.Sbb.Sbb.user.SiteUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecommenderRepository extends JpaRepository<RecommenderEntity, Integer> {
    @Query("SELECT DISTINCT  FROM RecommenderEntity INNER JOIN  r.answerList a WHERE a.id = :answerId")
    public SiteUserEntity findUserByIdWithQuestion(Integer recommenderId, Integer questionId);
}
