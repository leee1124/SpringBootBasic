package com.example.Sbb.Sbb.Service;

import com.example.Sbb.Sbb.Entity.AnswerEntity;
import com.example.Sbb.Sbb.Entity.QuestionEntity;
import com.example.Sbb.Sbb.Entity.SiteUserEntity;
import com.example.Sbb.Sbb.Repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(QuestionEntity questionEntity, String content, SiteUserEntity author){
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setContent(content);
        answerEntity.setCreateDate(LocalDateTime.now());
        answerEntity.setQuestion(questionEntity);
        answerEntity.setAuthor(author);
        this.answerRepository.save(answerEntity);
    }
}
