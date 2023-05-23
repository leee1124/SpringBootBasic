package com.example.Sbb.Sbb.question;

import static com.example.Sbb.Sbb.answer.QAnswerEntity.answerEntity;
import static com.example.Sbb.Sbb.question.QQuestionEntity.questionEntity;
import static com.example.Sbb.Sbb.user.QSiteUserEntity.siteUserEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
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

    private BooleanExpression search(String keyword) {
        return questionEntity.subject.like("%" + keyword + "%")
                .or(questionEntity.content.like("%" + keyword + "%"))
                .or(siteUserEntity.username.like("%" + keyword + "%"))
                .or(answerEntity.content.like("%" + keyword + "%"))
                .or(answerEntity.author.username.like("%" + keyword + "%"));
    }
    @Override
    public List<QuestionEntity> getQuestions(String keyword) {
        return jpaQueryFactory.selectFrom(questionEntity)
                .leftJoin(siteUserEntity).on(siteUserEntity.id.eq(questionEntity.author.id)).fetchJoin()
                .leftJoin(answerEntity).on(answerEntity.question.id.eq(questionEntity.id)).fetchJoin()
                .leftJoin(answerEntity.author).on(answerEntity.author.id.eq(siteUserEntity.id)).fetchJoin()
                .where(search(keyword))
                .distinct()
                .fetch();
    }
}
