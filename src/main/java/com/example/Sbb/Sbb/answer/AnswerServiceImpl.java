package com.example.Sbb.Sbb.answer;

import com.example.Sbb.Sbb.DataNotFoundException;
import com.example.Sbb.Sbb.question.QuestionDTO;
import com.example.Sbb.Sbb.question.QuestionService;
import com.example.Sbb.Sbb.recommend.RecommendService;
import com.example.Sbb.Sbb.user.SiteUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final RecommendService recommendService;

    public void create(QuestionDTO questionDTO, String content, SiteUserDTO siteUserDTO){
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(questionDTO.getId());
        answerDTO.setContent(content);
        answerDTO.setCreateDateTime(LocalDateTime.now());
        answerDTO.setModifyDateTime(LocalDateTime.now());
        answerDTO.setQuestion(questionDTO);
        answerDTO.setAuthor(siteUserDTO);
        this.answerRepository.save(this.toEntity(answerDTO));
    }

    public void delete(AnswerDTO answerDTO){
        this.answerRepository.delete(this.toEntity(answerDTO));
    }

    @Override
    public void modify(AnswerDTO answerDTO, String content) {
        answerDTO.setContent(content);
        answerDTO.setModifyDateTime(LocalDateTime.now());
        this.answerRepository.save(this.toEntity(answerDTO));
    }

    public AnswerDTO getAnswer(Integer id){
        AnswerEntity answerEntity = this.answerRepository.findById(id).orElseThrow(() -> new DataNotFoundException("answer not found"));
        AnswerDTO answerDTO = this.toDTO(answerEntity);
        return answerDTO;
    }

    public AnswerDTO toDTO(AnswerEntity answerEntity){

        return AnswerDTO.builder()
                .id(answerEntity.getId())
                .content(answerEntity.getContent())
                .createDateTime(answerEntity.getCreateDateTime())
                .modifyDateTime(answerEntity.getModifyDateTime())
                .recommend(recommendService.getAnswerRecommendCount(answerEntity.getId()))
                .question(questionService.toDTO(answerEntity.getQuestion()))
                .author(answerEntity.getAuthor().toDTO())
                .build();
    }
    public AnswerEntity toEntity(AnswerDTO answerDTO){

        return AnswerEntity.builder()
                .id(answerDTO.getId())
                .content(answerDTO.getContent())
                .createDateTime(answerDTO.getCreateDateTime())
                .modifyDateTime(answerDTO.getModifyDateTime())
                .question(questionService.toEntity(answerDTO.getQuestion()))
                .author(answerDTO.getAuthor().toEntity())
                .build();
    }
    /**
     *     private Integer id;
     *     private String content;
     *     private LocalDateTime createDateTime;
     *     private LocalDateTime modifyDateTime;
     *     private QuestionDTO question;
     *     private SiteUserDTO author;
     */
    @Override
    public void recommend(AnswerDTO answerDTO, SiteUserDTO siteUserDTO) {

    }
}
