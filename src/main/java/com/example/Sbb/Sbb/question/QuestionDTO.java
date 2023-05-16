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
    public QuestionEntity toEntity(){
        return QuestionEntity.builder()
                .id(id)
                .subject(subject)
                .content(content)
                .createDateTime(createDateTime)
                .modifyDateTime(modifyDateTime)
                .answerList(convertAnswerDTOListToAnswerEntityList(answerList))
                .author(author.toEntity())
                .build();
    }

    /**
     * List<AnswerDTO>를 List<AnswerEntity>로 변환
     * @param answerDTOList
     * @return
     */
    private List<AnswerEntity> convertAnswerDTOListToAnswerEntityList(List<AnswerDTO> answerDTOList){
        List<AnswerEntity> answerEntityList = new ArrayList<>();
        for (AnswerDTO answerDTO : answerDTOList) {
            answerEntityList.add(answerDTO.toEntity());
        }
        return answerEntityList;
    }
}
