package com.example.Sbb.Sbb.Entity;

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

    @ManyToOne
    private SiteUserEntity author;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
//
    @ManyToOne
    private QuestionEntity question;
}
