package com.example.Sbb.Sbb.question;

import com.example.Sbb.Sbb.user.SiteUserDTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class QuestionDTO {
    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime createDateTime;
    private LocalDateTime modifyDateTime;
    private List<Answer> answerList;
    private SiteUserDTO author;

    @Getter
    @Setter
    public static class Answer {
        private Integer id;
        private String content;
        private LocalDateTime createDateTime;
        private LocalDateTime modifyDateTime;
        private SiteUserDTO author;
    }

    public QuestionDTO() {
        this.answerList = new ArrayList<>();
    }

}

