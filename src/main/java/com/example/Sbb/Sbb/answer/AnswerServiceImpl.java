package com.example.Sbb.Sbb.answer;

import com.example.Sbb.Sbb.DataNotFoundException;
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
        answerDTO.setQuestion(questionDTO);
        answerDTO.setAuthor(siteUserDTO);
        this.answerRepository.save(answerDTO.toEntity());
    }

    public void delete(AnswerDTO answerDTO){
        this.answerRepository.delete(answerDTO.toEntity());
    }

    @Override
    public void modify(AnswerDTO answerDTO, String content) {
        answerDTO.setContent(content);
        answerDTO.setModifyDateTime(LocalDateTime.now());
        this.answerRepository.save(answerDTO.toEntity());
    }

    public AnswerDTO getAnswer(Integer id){
        AnswerEntity answerEntity = this.answerRepository.findById(id).orElseThrow(() -> new DataNotFoundException("answer not found"));
        AnswerDTO answerDTO = answerEntity.toDTO();
        return answerDTO;
    }

}
