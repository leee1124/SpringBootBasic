package com.example.Sbb.Sbb.answer;

import com.example.Sbb.Sbb.user.SiteUserEntity;
import com.example.Sbb.Sbb.question.QuestionEntity;
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
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
//
    @ManyToOne
    private QuestionEntity question;

    @ManyToOne
    private SiteUserEntity author;

    public AnswerDTO toDTO(){
        return AnswerDTO.builder()
                .id(id)
                .content(content)
                .createDate(createDate)
                .question(question)
                .author(author)
                .build();
    }

}
