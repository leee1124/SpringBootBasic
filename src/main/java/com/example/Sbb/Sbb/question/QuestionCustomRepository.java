package com.example.Sbb.Sbb.question;

import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionCustomRepository {

    QueryResults<QuestionEntity> getQuestions(String keywords, Pageable pageable);
    public QueryResults<QuestionEntity> getAll(Pageable pageable);
}
