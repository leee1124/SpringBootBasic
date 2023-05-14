package com.example.Sbb.Sbb.question;

import com.example.Sbb.Sbb.answer.AnswerEntity;
import com.example.Sbb.Sbb.user.SiteUserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDTO {
    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime createDateTime;
    private LocalDateTime modifyDateTime;
    private List<AnswerEntity> answerList;
    private SiteUserEntity author;
    public QuestionEntity toEntity(){
        return QuestionEntity.builder()
                .id(id)
                .subject(subject)
                .content(content)
                .createDateTime(createDateTime)
                .modifyDateTime(modifyDateTime)
                .answerList(answerList)
                .author(author)
                .build();
    }
}
