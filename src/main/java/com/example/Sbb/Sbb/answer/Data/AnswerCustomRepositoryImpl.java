package com.example.Sbb.Sbb.answer.Data;

import com.example.Sbb.Sbb.user.Data.SiteUserEntity;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import static com.example.Sbb.Sbb.answer.Data.QAnswerEntity.answerEntity;

public class AnswerCustomRepositoryImpl implements AnswerCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;


    public AnswerCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }
    @Override
    public QueryResults<AnswerEntity> getAnswersByAuthor(SiteUserEntity user, Pageable pageable) {
        return jpaQueryFactory.selectFrom(answerEntity)
                .where(answerEntity.author.id.eq(user.getId()))
                .orderBy(answerEntity.question.createDateTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }
}
