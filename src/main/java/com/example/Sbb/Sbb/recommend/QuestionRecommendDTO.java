package com.example.Sbb.Sbb.recommend;

import com.example.Sbb.Sbb.question.QuestionDTO;
import com.example.Sbb.Sbb.user.SiteUserDTO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRecommendDTO {

    private Long id;
    private QuestionDTO questionDTO;
    private SiteUserDTO siteUserDTO;
}
