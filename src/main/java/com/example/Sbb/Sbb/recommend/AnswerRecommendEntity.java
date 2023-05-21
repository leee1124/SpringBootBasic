package com.example.Sbb.Sbb.recommend;


import com.example.Sbb.Sbb.answer.AnswerEntity;
import com.example.Sbb.Sbb.user.SiteUserEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRecommendEntity {
    @Id
    private Long id;

    @ManyToOne
    private AnswerEntity answerEntity;

    @ManyToOne
    private SiteUserEntity siteUserEntity;

    public AnswerRecommendEntity(SiteUserEntity siteUserEntity, AnswerEntity answerEntity){
        this.answerEntity = answerEntity;
        this.siteUserEntity = siteUserEntity;
    }


}
