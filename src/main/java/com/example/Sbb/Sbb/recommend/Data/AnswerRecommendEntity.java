package com.example.Sbb.Sbb.recommend.Data;


import com.example.Sbb.Sbb.answer.Data.AnswerEntity;
import com.example.Sbb.Sbb.user.Data.SiteUserEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRecommendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
