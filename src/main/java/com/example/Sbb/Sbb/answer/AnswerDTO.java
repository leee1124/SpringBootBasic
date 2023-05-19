package com.example.Sbb.Sbb.answer;


import com.example.Sbb.Sbb.question.QuestionDTO;
import com.example.Sbb.Sbb.user.SiteUserDTO;
import com.example.Sbb.Sbb.user.SiteUserEntity;
import lombok.*;

import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.Set;

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

    /**
     * 한 사람이 여러 질문을 추천할 수 있고, 한 질문은 여러 사람에게 추천받을 수 있음
     * 따라서, ManyToMany를 사용
     * @ManyToMany관계로 속성을 생성하면 새로운 테이블을 생성하여 데이터 관리
     * 테이블에는 서로 연관된 엔티티의 고유번호 2개가 PK로 되어있어 다대다 관계가 성립
     */
    @ManyToMany
    private Set<SiteUserDTO> recommender;

}
