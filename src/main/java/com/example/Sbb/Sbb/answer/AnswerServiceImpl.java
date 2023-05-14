package com.example.Sbb.Sbb.answer;

import com.example.Sbb.Sbb.question.QuestionDTO;
import com.example.Sbb.Sbb.user.SiteUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    public void create(QuestionDTO questionDTO, String content, SiteUserDTO siteUserDTO){
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(questionDTO.getId());
        answerDTO.setContent(content);
        answerDTO.setCreateDateTime(LocalDateTime.now());
        answerDTO.setModifyDateTime(LocalDateTime.now());
        answerDTO.setQuestion(questionDTO.toEntity());
        answerDTO.setAuthor(siteUserDTO.toEntity());
        this.answerRepository.save(answerDTO.toEntity());
    }

}
