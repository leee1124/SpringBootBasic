package com.example.Sbb.Sbb.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private SiteUserEntity author;

    @Column(length = 300)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDateTime;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<AnswerEntity> answerList;

    @ManyToOne
    private SiteUserEntity author;
}
