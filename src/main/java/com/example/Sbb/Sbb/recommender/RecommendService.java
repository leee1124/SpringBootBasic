package com.example.Sbb.Sbb.recommender;

import com.example.Sbb.Sbb.question.QuestionEntity;
import com.example.Sbb.Sbb.user.SiteUserEntity;

public interface RecommendService {
    public SiteUserEntity getSiteUser(RecommenderEntity recommenderEntity);
    public QuestionEntity getQuestion(SiteUserEntity siteUserEntity);
}
