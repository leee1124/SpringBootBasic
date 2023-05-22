package com.example.Sbb.Sbb.recommend;


import com.example.Sbb.Sbb.answer.AnswerEntity;
import com.example.Sbb.Sbb.user.SiteUserEntity;

public interface AnswerRecommendService {

    public Long getAnswerRecommendCount(Long answerId);

    public void recommend(SiteUserEntity siteUserEntity, AnswerEntity answerEntity);
}
