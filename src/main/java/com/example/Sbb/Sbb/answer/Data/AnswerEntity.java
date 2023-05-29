package com.example.Sbb.Sbb.answer.Data;

import com.example.Sbb.Sbb.user.Data.SiteUserEntity;
import com.example.Sbb.Sbb.question.Data.QuestionEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDateTime;

    private LocalDateTime modifyDateTime;
    @ManyToOne
    private QuestionEntity question;

    @ManyToOne
    private SiteUserEntity author;
}
