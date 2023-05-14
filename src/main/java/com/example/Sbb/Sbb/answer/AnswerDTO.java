package com.example.Sbb.Sbb.answer;

import com.example.Sbb.Sbb.answer.AnswerEntity;
import com.example.Sbb.Sbb.question.QuestionEntity;
import com.example.Sbb.Sbb.user.SiteUserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDTO {

    private Integer id;
    private String content;
    private LocalDateTime createDateTime;
    private LocalDateTime modifyDateTime;
    private QuestionEntity question;
    private SiteUserEntity author;

    public AnswerEntity toEntity(){
        return AnswerEntity.builder()
                .id(id)
                .content(content)
                .createDateTime(createDateTime)
                .modifyDateTime(modifyDateTime)
                .question(question)
                .author(author)
                .build();
    }
}
