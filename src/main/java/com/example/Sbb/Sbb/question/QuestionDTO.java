package com.example.Sbb.Sbb.question;

import com.example.Sbb.Sbb.answer.AnswerDTO;
import com.example.Sbb.Sbb.answer.AnswerEntity;
import com.example.Sbb.Sbb.user.SiteUserDTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private List<AnswerDTO> answerList;
    private SiteUserDTO author;


}

