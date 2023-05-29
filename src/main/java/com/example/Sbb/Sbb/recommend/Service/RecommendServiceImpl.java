package com.example.Sbb.Sbb.recommend.Service;


import com.example.Sbb.Sbb.DataNotFoundException;
import com.example.Sbb.Sbb.answer.Data.AnswerEntity;
import com.example.Sbb.Sbb.question.Data.QuestionEntity;
import com.example.Sbb.Sbb.recommend.Data.AnswerRecommendEntity;
import com.example.Sbb.Sbb.recommend.Data.AnswerRecommendRepository;
import com.example.Sbb.Sbb.recommend.Data.QuestionRecommendEntity;
import com.example.Sbb.Sbb.recommend.Data.QuestionRecommendRepository;
import com.example.Sbb.Sbb.user.Data.SiteUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecommendServiceImpl implements RecommendService {
    private final QuestionRecommendRepository questionRecommendRepository;
    private final AnswerRecommendRepository answerRecommendRepository;

    public Long getAnswerRecommendCount(Long answerId){
        return answerRecommendRepository.findAnswerRecommendNum(answerId);
    }

    public Long getQuestionRecommendCount(Long questionId){
        return questionRecommendRepository.findQuestionRecommendNum(questionId);
    }

    public void recommend(SiteUserEntity siteUserEntity, QuestionEntity questionEntity) {
        this.questionRecommendRepository.questionExist(siteUserEntity.getId(), questionEntity.getId())
                .ifPresent(
                        (id) -> {
                            throw new DataNotFoundException("이미 추천하셨습니다.");
                        });
        QuestionRecommendEntity questionRecommendEntity = new QuestionRecommendEntity(siteUserEntity,questionEntity);

        questionRecommendRepository.save(questionRecommendEntity);

    }

    @Override
    public void recommend(SiteUserEntity siteUserEntity, AnswerEntity answerEntity) {
        this.answerRecommendRepository.answerExist(siteUserEntity.getId(), answerEntity.getId())
                .ifPresent(
                        (id) -> {
                            throw new DataNotFoundException("이미 추천하셨습니다.");
                        });
        AnswerRecommendEntity answerRecommendEntity = new AnswerRecommendEntity(siteUserEntity,answerEntity);
        answerRecommendRepository.save(answerRecommendEntity);

    }
}
