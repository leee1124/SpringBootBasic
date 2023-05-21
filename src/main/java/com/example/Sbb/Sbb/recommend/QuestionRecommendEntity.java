package com.example.Sbb.Sbb.recommend;

import com.example.Sbb.Sbb.question.QuestionEntity;
import com.example.Sbb.Sbb.user.SiteUserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRecommendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    SiteUserEntity siteUserEntity;

    @ManyToOne
    QuestionEntity questionEntity;

    public QuestionRecommendEntity(SiteUserEntity siteUserEntity, QuestionEntity questionEntity){
        this.questionEntity = questionEntity;
        this.siteUserEntity = siteUserEntity;
    }
}
