package com.example.Sbb.Sbb.answer.Service;

import com.example.Sbb.Sbb.answer.Data.AnswerDTO;
import com.example.Sbb.Sbb.answer.Data.AnswerEntity;
import com.example.Sbb.Sbb.question.Data.QuestionDTO;
import com.example.Sbb.Sbb.user.Data.SiteUserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AnswerService {
    public void create(QuestionDTO questionDTO, String content, SiteUserDTO siteUserDTO);
    public void delete(AnswerDTO answerDTO);
    public void modify(AnswerDTO answerDTO, String content);
    public void recommend(AnswerDTO answerDTO, SiteUserDTO siteUserDTO);
    public AnswerDTO getAnswer(Long id);
    public List<AnswerDTO> getAnswers(SiteUserDTO siteUserDTO);
    public AnswerDTO toDTO(AnswerEntity answerEntity);
    public AnswerEntity toEntity(AnswerDTO answerDTO);
    public Page<AnswerDTO> getAnswers(int page, int size, SiteUserDTO siteUserDTO);
}
