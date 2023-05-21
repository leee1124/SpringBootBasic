package com.example.Sbb.Sbb.recommend;


import com.example.Sbb.Sbb.question.QuestionDTO;
import com.example.Sbb.Sbb.question.QuestionEntity;
import com.example.Sbb.Sbb.user.SiteUserDTO;
import com.example.Sbb.Sbb.user.SiteUserEntity;

public interface RecommendService {
    public Long getAnswerRecommendCount(Long answerId);
    public Long getQuestionRecommendCount(Long questionId);
    public void recommend(SiteUserEntity siteUserEntity, QuestionEntity questionEntity);
}
