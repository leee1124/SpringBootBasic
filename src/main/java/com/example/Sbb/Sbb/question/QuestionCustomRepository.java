package com.example.Sbb.Sbb.question;

import java.util.List;

public interface QuestionCustomRepository {

    List<QuestionEntity> getQuestions(String keyword);
}
