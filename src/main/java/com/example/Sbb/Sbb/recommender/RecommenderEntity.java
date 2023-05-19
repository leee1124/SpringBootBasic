package com.example.Sbb.Sbb.recommender;

import com.example.Sbb.Sbb.question.QuestionEntity;
import com.example.Sbb.Sbb.user.SiteUserEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Getter
@Entity
public class RecommenderEntity {
    @Id
    private Integer id;
    @OneToMany
    private Set<QuestionEntity> QuestionEntity;
    @OneToMany
    private Set<SiteUserEntity> siteUserEntity;

}
