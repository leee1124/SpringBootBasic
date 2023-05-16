package com.example.Sbb.Sbb.question;

import com.example.Sbb.Sbb.answer.AnswerDTO;
import com.example.Sbb.Sbb.answer.AnswerEntity;
import com.example.Sbb.Sbb.user.SiteUserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 300)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDateTime;

    private LocalDateTime modifyDateTime;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<AnswerEntity> answerList;

    @ManyToOne
    private SiteUserEntity author;


    public QuestionDTO toDTO(){
        return QuestionDTO.builder()
                .id(id)
                .subject(subject)
                .content(content)
                .createDateTime(createDateTime)
                .modifyDateTime(modifyDateTime)
                .answerList(convertAnswerEntityListToAnswerDTOList(answerList))
                .author(author.toDTO())
                .build();
    }

    private List<AnswerDTO> convertAnswerEntityListToAnswerDTOList(List<AnswerEntity> answerList){
        List<AnswerDTO> answerDTOList = new ArrayList<>();
        for(AnswerEntity answerEntity: answerList){
            answerDTOList.add(answerEntity.toDTO());
        }
        return answerDTOList;
    }
}
