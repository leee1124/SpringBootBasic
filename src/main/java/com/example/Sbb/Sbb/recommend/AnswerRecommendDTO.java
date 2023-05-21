package com.example.Sbb.Sbb.recommend;

import com.example.Sbb.Sbb.answer.AnswerDTO;
import com.example.Sbb.Sbb.user.SiteUserDTO;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRecommendDTO {
    private Long id;
    private AnswerDTO answerDTO;
    private SiteUserDTO siteUserDTO;
}
