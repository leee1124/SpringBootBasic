package com.example.Sbb.Sbb.recommend.Data;

import com.example.Sbb.Sbb.answer.Data.AnswerDTO;
import com.example.Sbb.Sbb.user.Data.SiteUserDTO;
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
