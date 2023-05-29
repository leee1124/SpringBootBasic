package com.example.Sbb.Sbb.recommend.Data;

import java.util.Optional;

public interface RecommendCustomRepository {
    public Optional<AnswerRecommendEntity> answerExist(Long siteUserId, Long answerId);
    public Optional<QuestionRecommendEntity> questionExist(Long siteUserId, Long questionId);
    public Long findAnswerRecommendNum(Long answerId);
    public Long findQuestionRecommendNum(Long questionId);
}
