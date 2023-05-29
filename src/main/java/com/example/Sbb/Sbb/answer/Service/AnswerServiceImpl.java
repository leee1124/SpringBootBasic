package com.example.Sbb.Sbb.answer.Service;

import com.example.Sbb.Sbb.DataNotFoundException;
import com.example.Sbb.Sbb.answer.Data.AnswerDTO;
import com.example.Sbb.Sbb.answer.Data.AnswerEntity;
import com.example.Sbb.Sbb.answer.Data.AnswerRepository;
import com.example.Sbb.Sbb.question.Data.QuestionDTO;
import com.example.Sbb.Sbb.question.Data.QuestionEntity;
import com.example.Sbb.Sbb.question.Service.QuestionService;
import com.example.Sbb.Sbb.recommend.Data.AnswerRecommendService;
import com.example.Sbb.Sbb.user.Data.SiteUserDTO;
import com.querydsl.core.QueryResults;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final AnswerRecommendService recommendService;

    public void create(QuestionDTO questionDTO, String content, SiteUserDTO siteUserDTO){
        AnswerDTO answerDTO = new AnswerDTO();
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

    public AnswerDTO getAnswer(Long id){
        AnswerEntity answerEntity = this.answerRepository.findById(id).orElseThrow(() -> new DataNotFoundException("answer not found"));
        AnswerDTO answerDTO = this.toDTO(answerEntity);
        return answerDTO;
    }

    @Override
    public List<AnswerDTO> getAnswers(SiteUserDTO siteUserDTO) {
        List<AnswerEntity> answerEntityList = this.answerRepository.findByAuthor(siteUserDTO.toEntity())
                .orElseThrow(()-> new DataNotFoundException("answer not found"));
        return answerEntityList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Page<AnswerDTO> getAnswers(int page, int size, SiteUserDTO siteUserDTO) {
        Pageable pageable = PageRequest.of(page,size);

        QueryResults<AnswerEntity> queryResults = this.answerRepository.getAnswersByAuthor(siteUserDTO.toEntity(), pageable);
        Long totalCount = queryResults.getTotal();

        List<AnswerDTO> answerDTOList = queryResults.getResults()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(answerDTOList,pageable,totalCount);
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
        recommendService.recommend(siteUserDTO.toEntity(), this.toEntity(answerDTO));
    }
}
