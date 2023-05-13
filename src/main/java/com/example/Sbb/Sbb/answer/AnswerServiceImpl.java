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

    public AnswerDTO create(QuestionDTO questionDTO, String content, SiteUserDTO siteUserDTO){
        AnswerDTO answerDTO = new AnswerDTO(questionDTO.getId(), content, LocalDateTime.now(), questionDTO.toEntity(), siteUserDTO.toEntity());
        this.answerRepository.save(answerDTO.toEntity());
        return answerDTO;
    }
}
