package com.example.Sbb.Sbb.recommend.Data;


import com.example.Sbb.Sbb.answer.Data.AnswerEntity;
import com.example.Sbb.Sbb.user.Data.SiteUserEntity;

public interface AnswerRecommendService {

    public Long getAnswerRecommendCount(Long answerId);

    public void recommend(SiteUserEntity siteUserEntity, AnswerEntity answerEntity);
}
