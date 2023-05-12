package com.example.Sbb.Sbb.answer;

import com.example.Sbb.Sbb.question.QuestionEntity;
import com.example.Sbb.Sbb.user.SiteUserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AnswerDTO {

    private Integer id;
    private String content;
    private LocalDateTime createDate;
    private QuestionEntity question;
    private SiteUserEntity author;

    public AnswerEntity toEntity(){
        return AnswerEntity.builder()
                .id(id)
                .content(content)
                .createDate(createDate)
                .question(question)
                .author(author)
                .build();
    }
}
