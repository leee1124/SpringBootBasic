package com.example.Sbb.Sbb.question.Data;

import com.example.Sbb.Sbb.answer.Data.AnswerEntity;
import com.example.Sbb.Sbb.user.Data.SiteUserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;


    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<AnswerEntity> answerList;

    @ManyToOne
    private SiteUserEntity author;
    @Column(columnDefinition = "long default 0", nullable = false)
    private Long view;
    private LocalDateTime createDateTime;

    private LocalDateTime modifyDateTime;

}
