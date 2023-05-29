package com.example.Sbb.Sbb.recommend.Data;

import com.example.Sbb.Sbb.question.Data.QuestionEntity;
import com.example.Sbb.Sbb.user.Data.SiteUserEntity;

public interface QuestionRecommendService {

    public Long getQuestionRecommendCount(Long questionId);
    public void recommend(SiteUserEntity siteUserEntity, QuestionEntity questionEntity);
}
