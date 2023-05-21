package com.example.Sbb.Sbb.recommend;


import com.example.Sbb.Sbb.question.QuestionEntity;
import com.example.Sbb.Sbb.user.SiteUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RecommendServiceImpl implements RecommendService {
    private final RecommendRepository recommendRepository;

    public Long getAnswerRecommendCount(Long answerId){
        return recommendRepository.findAnswerRecommendNum(answerId);
    }

    public Long getQuestionRecommendCount(Long questionId){
        return recommendRepository.findQuestionRecommendNum(questionId);
    }

    public void recommend(SiteUserEntity siteUserEntity, QuestionEntity questionEntity) {
        this.recommendRepository.questionExist(siteUserEntity.getId(), questionEntity.getId())
                .ifPresent(
                        (id) -> {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 추천하셨습니다.");
                        });
        QuestionRecommendEntity questionRecommendEntity = new QuestionRecommendEntity(siteUserEntity,questionEntity);

        recommendRepository.save(questionRecommendEntity);

    }
}
