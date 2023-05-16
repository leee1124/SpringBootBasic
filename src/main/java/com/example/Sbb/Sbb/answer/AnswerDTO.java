package com.example.Sbb.Sbb.answer;


import com.example.Sbb.Sbb.question.QuestionDTO;
import com.example.Sbb.Sbb.user.SiteUserDTO;
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
    private QuestionDTO question;
    private SiteUserDTO author;

    public AnswerEntity toEntity(){
        return AnswerEntity.builder()
                .id(id)
                .content(content)
                .createDateTime(createDateTime)
                .modifyDateTime(modifyDateTime)
                .question(question.toEntity())
                .author(author.toEntity())
                .build();
    }
}
