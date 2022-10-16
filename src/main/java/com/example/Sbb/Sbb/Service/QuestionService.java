package com.example.Sbb.Sbb.Service;

import com.example.Sbb.Sbb.DataNotFoundException;
import com.example.Sbb.Sbb.Entity.QuestionEntity;
import com.example.Sbb.Sbb.Repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<QuestionEntity> getList(){
        return this.questionRepository.findAll();
    }


    public QuestionEntity getQuestion(Integer id){
        Optional<QuestionEntity> questionEntity = this.questionRepository.findById(id);
        if(questionEntity.isPresent()) {
            return questionEntity.get();
        }else{
            throw new DataNotFoundException("question not found");
        }
    }
}
