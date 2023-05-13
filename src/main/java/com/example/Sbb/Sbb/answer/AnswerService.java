package com.example.Sbb.Sbb.answer;

import com.example.Sbb.Sbb.question.QuestionDTO;
import com.example.Sbb.Sbb.user.SiteUserDTO;

public interface AnswerService {
    public AnswerDTO create(QuestionDTO questionDTO, String content, SiteUserDTO siteUserDTO);
}
