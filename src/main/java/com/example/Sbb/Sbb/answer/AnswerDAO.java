package com.example.Sbb.Sbb.answer;

public interface AnswerDAO {
    AnswerEntity saveAnswer(AnswerEntity answerEntity);
    AnswerEntity getAnswer(Integer id);
}
