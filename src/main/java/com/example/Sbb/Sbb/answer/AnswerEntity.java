package com.example.Sbb.Sbb.answer;

import com.example.Sbb.Sbb.user.SiteUserEntity;
import com.example.Sbb.Sbb.question.QuestionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
//
    @ManyToOne
    private QuestionEntity question;

    @ManyToOne
    private SiteUserEntity author;
}