package com.example.Sbb.Sbb.answer;

import com.example.Sbb.Sbb.question.QuestionEntity;
import com.example.Sbb.Sbb.user.SiteUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerEntity create(QuestionEntity questionEntity, String content, SiteUserEntity author){
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setContent(content);
        answerEntity.setCreateDate(LocalDateTime.now());
        answerEntity.setQuestion(questionEntity);
        answerEntity.setAuthor(author);
        this.answerRepository.save(answerEntity);
        return answerEntity;
    }
}
