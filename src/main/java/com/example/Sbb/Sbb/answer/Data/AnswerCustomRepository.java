package com.example.Sbb.Sbb.answer.Data;

import com.example.Sbb.Sbb.user.Data.SiteUserEntity;
import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Pageable;

public interface AnswerCustomRepository {
    public QueryResults<AnswerEntity> getAnswersByAuthor(SiteUserEntity user, Pageable pageable);
}
