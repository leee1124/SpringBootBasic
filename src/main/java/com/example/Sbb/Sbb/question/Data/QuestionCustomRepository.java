package com.example.Sbb.Sbb.question.Data;

import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Pageable;

public interface QuestionCustomRepository {

    QueryResults<QuestionEntity> getQuestions(String keywords, Pageable pageable);
    public QueryResults<QuestionEntity> getAll(Pageable pageable);
}
