package com.example.Sbb.Sbb.recommend;

import com.example.Sbb.Sbb.question.QuestionEntity;
import com.example.Sbb.Sbb.user.SiteUserEntity;

public interface QuestionRecommendService {

    public Long getQuestionRecommendCount(Long questionId);
    public void recommend(SiteUserEntity siteUserEntity, QuestionEntity questionEntity);
}
