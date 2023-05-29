package com.example.Sbb.Sbb.recommend.Data;

import com.example.Sbb.Sbb.question.Data.QuestionDTO;
import com.example.Sbb.Sbb.user.Data.SiteUserDTO;
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
