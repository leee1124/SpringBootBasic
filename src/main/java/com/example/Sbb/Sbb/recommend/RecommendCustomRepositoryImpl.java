package com.example.Sbb.Sbb.recommend;


import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;


import static com.example.Sbb.Sbb.recommend.QAnswerRecommendEntity.answerRecommendEntity;
import static com.example.Sbb.Sbb.recommend.QQuestionRecommendEntity.questionRecommendEntity;

@Repository
@Transactional
public class RecommendCustomRepositoryImpl implements RecommendCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public RecommendCustomRepositoryImpl(EntityManager entityManager){
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    public Optional<AnswerRecommendEntity> answerExist(Long siteUserId, Long answerId){
        AnswerRecommendEntity are = jpaQueryFactory
                .selectFrom(answerRecommendEntity)
                .where(answerRecommendEntity.siteUserEntity.id.eq(siteUserId),
                        answerRecommendEntity.answerEntity.id.eq(answerId))
                .fetchFirst();
        return Optional.ofNullable(are);

    }

    public Optional<QuestionRecommendEntity> questionExist(Long siteUserId, Long questionId){
        QuestionRecommendEntity qre = jpaQueryFactory
                .selectFrom(questionRecommendEntity)
                .where(questionRecommendEntity.siteUserEntity.id.eq(siteUserId),
                        questionRecommendEntity.questionEntity.id.eq(questionId))
                .fetchFirst();
        return Optional.ofNullable(qre);

    }
    public Long findAnswerRecommendNum(Long answerId){
        long query = jpaQueryFactory
                .selectFrom(answerRecommendEntity)
                .where(answerRecommendEntity.answerEntity.id.eq(answerId))
                .fetchCount();
        return query;
    }

    public Long findQuestionRecommendNum(Long questionId){
        long query =  jpaQueryFactory
                .selectFrom(questionRecommendEntity)
                .where(questionRecommendEntity.questionEntity.id.eq(questionId))
                .fetchCount();
        return query;
    }
}

