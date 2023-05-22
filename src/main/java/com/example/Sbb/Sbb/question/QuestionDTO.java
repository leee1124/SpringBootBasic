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
    private Long id;
    private String subject;
    private String content;
    private LocalDateTime createDateTime;
    private LocalDateTime modifyDateTime;
    private List<Answer> answerList;
    private SiteUserDTO author;
    private Long recommend;




    /**
     * QuestionDTO안에 있는 static Answer클래스는 question 정보를 알 필요가 없음
     * QuestionDTO에 이미 question 정보가 담겨있기 때문
     * 따라서, AnswerEntity와는 다르게 Question 정보를 빼고, 무한순환참조를 막음
     */
    @Getter
    @Setter
    public static class Answer {
        private Long id;
        private String content;
        private LocalDateTime createDateTime;
        private LocalDateTime modifyDateTime;
        private SiteUserDTO author;
        private Long recommend;
    }




    /**
     * 파라미터가 존재하지 않는 생성자에서 answerList, recommenderSet을 생성해 NullPointerException 방지
     */
    public QuestionDTO() {
        this.answerList = new ArrayList<>();
    }
}

