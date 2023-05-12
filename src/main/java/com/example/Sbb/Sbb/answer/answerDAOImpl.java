package com.example.Sbb.Sbb.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class answerDAOImpl implements AnswerDAO{
    private final AnswerRepository answerRepository;
    @Override
    public AnswerEntity saveAnswer(AnswerEntity answerEntity) {
        return answerRepository.save(answerEntity);
    }

    @Override
    public AnswerEntity getAnswer(Integer id) {
        return answerRepository.getById(id);
    }
}
