package com.example.Sbb.Sbb.answer;

import com.example.Sbb.Sbb.user.SiteUserEntity;
import com.example.Sbb.Sbb.question.QuestionEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDateTime;

    private LocalDateTime modifyDateTime;
    @ManyToOne
    private QuestionEntity question;

    @ManyToOne
    private SiteUserEntity author;

    @ManyToMany
    private Set<SiteUserEntity> recommender;

}
