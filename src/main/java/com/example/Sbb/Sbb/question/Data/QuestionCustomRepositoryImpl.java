package com.example.Sbb.Sbb.question.Data;

import static com.example.Sbb.Sbb.question.Data.QQuestionEntity.questionEntity;

import com.example.Sbb.Sbb.user.Data.SiteUserEntity;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;

@Transactional
@Repository
public class QuestionCustomRepositoryImpl implements QuestionCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;


    public QuestionCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    private BooleanExpression search(String keywords) {
        return questionEntity.subject.like("%" + keywords + "%")
                .or(questionEntity.content.like("%" + keywords + "%"))
                .or(questionEntity.author.username.like("%" + keywords + "%"));
    }
    @Override
    public QueryResults<QuestionEntity> getQuestions(String keywords, Pageable pageable) {
        return jpaQueryFactory.selectFrom(questionEntity)
                .where(search(keywords))
                .orderBy(questionEntity.createDateTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }

    @Override
    public QueryResults<QuestionEntity> getQuestionsByAuthor(SiteUserEntity user, Pageable pageable) {
        return jpaQueryFactory.selectFrom(questionEntity)
                .where(questionEntity.author.id.eq(user.getId()))
                .orderBy(questionEntity.createDateTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }

    public QueryResults<QuestionEntity> getAll(Pageable pageable){
        return jpaQueryFactory.selectFrom(questionEntity)
                .orderBy(questionEntity.createDateTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }


}
