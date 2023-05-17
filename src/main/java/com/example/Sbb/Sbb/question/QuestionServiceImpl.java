package com.example.Sbb.Sbb.question;

import com.example.Sbb.Sbb.DataNotFoundException;
import com.example.Sbb.Sbb.answer.AnswerDTO;
import com.example.Sbb.Sbb.answer.AnswerEntity;
import com.example.Sbb.Sbb.answer.AnswerRepository;
import com.example.Sbb.Sbb.user.SiteUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public List<QuestionEntity> getList(){
        return this.questionRepository.findAll();
    }


    public QuestionDTO getQuestion(Integer id){
        QuestionEntity questionEntity = this.questionRepository.findById(id).orElseThrow(() -> new DataNotFoundException("question not found"));
        QuestionDTO questionDTO = this.toDTO(questionEntity);
        return questionDTO;
    }

    public void create(String subject, String content, SiteUserDTO siteUserDTO){
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setSubject(subject);
        questionDTO.setContent(content);
        questionDTO.setCreateDateTime(LocalDateTime.now());
        questionDTO.setModifyDateTime(LocalDateTime.now());
        questionDTO.setAuthor(siteUserDTO);
        this.questionRepository.save(this.toEntity(questionDTO));
    }

    public void modify(QuestionDTO questionDTO, String subject, String content){
        questionDTO.setSubject(subject);
        questionDTO.setContent(content);
        questionDTO.setModifyDateTime(LocalDateTime.now());
        this.questionRepository.save(this.toEntity(questionDTO));
    }

    public void delete(QuestionDTO questionDTO){
        this.questionRepository.delete(this.toEntity(questionDTO));
    }

    /**
     * 정수 타입의 페이지 번호를 입력받아 해당 페이지의 질문 목록을 리턴
     * PageRequest.of(page, 10)에서 page는 조회할 페이지의 번호이고, 10은 한 페이지에 보여줄 게시물의 개수
     */
    public Page<QuestionEntity> getList(int page){
        /**
         * 게시물을 역순으로 조회하기 위해서 PageRequest.of 메소드의 세번째 파라미터로 Sort 객체 전달해야함
         * Sort.Order 객체로 구성된 리스트에 Sort.Order 객체를 추가
         * Sort.by(소트 리스트)로 소트 객체 생성 가능
         * Sort.Order.desc("createDate")는 작성일시를 역순으로 조회하기
         */
        List<Sort.Order> sort = new ArrayList<>();
        sort.add(Sort.Order.desc("createDateTime"));
        Pageable pageable = PageRequest.of(page, 15, Sort.by(sort));
        return this.questionRepository.findAll(pageable);
    }
    public QuestionEntity toEntity(QuestionDTO questionDTO){
        return QuestionEntity.builder()
                .id(questionDTO.getId())
                .subject(questionDTO.getSubject())
                .content(questionDTO.getContent())
                .createDateTime(questionDTO.getCreateDateTime())
                .modifyDateTime(questionDTO.getModifyDateTime())
                .answerList(convertAnswerDTOListToAnswerEntityList(questionDTO.getAnswerList()))
                .author(questionDTO.getAuthor().toEntity())
                .build();
    }
    private List<AnswerEntity> convertAnswerDTOListToAnswerEntityList(List<AnswerDTO> answerDTOList) {
        List<AnswerEntity> answerEntityList = new ArrayList<>();
        for (AnswerDTO answerDTO : answerDTOList) {
            AnswerEntity answerEntity = AnswerEntity.builder()
                    .id(answerDTO.getId())
                    .content(answerDTO.getContent())
                    .createDateTime(answerDTO.getCreateDateTime())
                    .modifyDateTime(answerDTO.getModifyDateTime())
                    .question(this.getQuestionEntity(answerDTO.getId()))
                    .build();
            // 추가 필드가 있다면 해당 필드도 설정
            // answerEntity.setXXX(answerDTO.getXXX());
            answerEntityList.add(answerEntity);
        }
        return answerEntityList;
    }

    private QuestionEntity getQuestionEntity(Integer id){
        QuestionEntity questionEntity = this.questionRepository.findById(id).orElseThrow(() -> new DataNotFoundException("question not found"));
        return questionEntity;
    }

    public QuestionDTO toDTO(QuestionEntity questionEntity){
        QuestionDTO questionDTO = QuestionDTO.builder()
                .id(questionEntity.getId())
                .subject(questionEntity.getSubject())
                .content(questionEntity.getContent())
                .createDateTime(questionEntity.getCreateDateTime())
                .modifyDateTime(questionEntity.getModifyDateTime())
                .answerList(convertAnswerEntityListToAnswerDTOList(questionEntity.getAnswerList()))
                .author(questionEntity.getAuthor().toDTO())
                .build();
        System.out.println("questionDTO = " + questionDTO);
        return questionDTO;
    }
    private List<AnswerDTO> convertAnswerEntityListToAnswerDTOList(List<AnswerEntity> answerEntityList) {
        List<AnswerDTO> answerDTOList = new ArrayList<>();
        for (AnswerEntity answerEntity : answerEntityList) {
            AnswerDTO answerDTO = AnswerDTO.builder()
                    .id(answerEntity.getId())
                    .content(answerEntity.getContent())
                    .createDateTime(answerEntity.getCreateDateTime())
                    .modifyDateTime(answerEntity.getModifyDateTime())
                    .question(this.getQuestionByAnswer(answerEntity.getId()))
                    .build();
            answerDTOList.add(answerDTO);
        }
        System.out.println("answerDTOList = " + answerDTOList);
        return answerDTOList;
    }

    public QuestionDTO getQuestionByAnswer(Integer id){
        QuestionEntity questionEntity = this.questionRepository.findQuestionWithAnswersById(id);
        QuestionDTO questionDTO = this.toDTO(questionEntity);
        return questionDTO;
    }



}
