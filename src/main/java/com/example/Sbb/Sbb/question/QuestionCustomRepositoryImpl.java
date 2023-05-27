package com.example.Sbb.Sbb.question;

import static com.example.Sbb.Sbb.answer.QAnswerEntity.answerEntity;
import static com.example.Sbb.Sbb.question.QQuestionEntity.questionEntity;
import static com.example.Sbb.Sbb.user.QSiteUserEntity.siteUserEntity;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import java.util.List;

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
                .orderBy()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }

    public QueryResults<QuestionEntity> getAll(Pageable pageable){
        return jpaQueryFactory.selectFrom(questionEntity)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }
}
