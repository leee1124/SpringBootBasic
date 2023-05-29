package com.example.Sbb.Sbb.question.Data;

import com.example.Sbb.Sbb.user.Data.SiteUserEntity;
import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Pageable;

public interface QuestionCustomRepository {

    QueryResults<QuestionEntity> getQuestions(String keywords, Pageable pageable);
    public QueryResults<QuestionEntity> getAll(Pageable pageable);
    public QueryResults<QuestionEntity> getQuestionsByAuthor(SiteUserEntity user, Pageable pageable);
}
