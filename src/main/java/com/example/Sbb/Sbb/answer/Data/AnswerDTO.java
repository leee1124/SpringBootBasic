package com.example.Sbb.Sbb.answer.Data;


import com.example.Sbb.Sbb.question.Data.QuestionDTO;
import com.example.Sbb.Sbb.user.Data.SiteUserDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDTO {

    private Long id;
    private String content;
    private LocalDateTime createDateTime;
    private LocalDateTime modifyDateTime;
    private QuestionDTO question;
    private SiteUserDTO author;
    private Long recommend;

}
