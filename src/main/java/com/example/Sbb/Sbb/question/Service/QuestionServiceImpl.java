package com.example.Sbb.Sbb.question.Service;

import com.example.Sbb.Sbb.DataNotFoundException;
import com.example.Sbb.Sbb.answer.Data.AnswerEntity;
import com.example.Sbb.Sbb.question.Data.QuestionDTO;
import com.example.Sbb.Sbb.question.Data.QuestionRepository;
import com.example.Sbb.Sbb.question.Data.QuestionEntity;
import com.example.Sbb.Sbb.recommend.Service.RecommendService;
import com.example.Sbb.Sbb.user.Data.SiteUserDTO;
import com.querydsl.core.QueryResults;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final RecommendService recommendService;

/*  getList 메소드를 이용하면 모든 질문들이 출력됨
    페이징을 하기 위해서 주석처리
    public List<QuestionEntity> getList() {
        return this.questionRepository.findAll();
    }
*/


    public QuestionDTO getQuestion(Long id) {
        QuestionEntity questionEntity = this.questionRepository.findById(id).orElseThrow(() -> new DataNotFoundException("question not found"));
         return this.toDTO(questionEntity);
    }

    public void create(String subject, String content, SiteUserDTO siteUserDTO) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setSubject(subject);
        questionDTO.setContent(content);
        questionDTO.setCreateDateTime(LocalDateTime.now());
        questionDTO.setModifyDateTime(LocalDateTime.now());
        questionDTO.setAuthor(siteUserDTO);
        this.questionRepository.save(this.toEntity(questionDTO));
    }

    public void modify(QuestionDTO questionDTO, String subject, String content) {
        questionDTO.setSubject(subject);
        questionDTO.setContent(content);
        questionDTO.setModifyDateTime(LocalDateTime.now());
        this.questionRepository.save(this.toEntity(questionDTO));
    }

    public void delete(QuestionDTO questionDTO) {
        this.questionRepository.delete(this.toEntity(questionDTO));
    }

    /**
     * 정수 타입의 페이지 번호를 입력받아 해당 페이지의 질문 목록을 리턴
     * PageRequest.of(page, 10)에서 page는 조회할 페이지의 번호이고, 10은 한 페이지에 보여줄 게시물의 개수
     */
    public Page<QuestionDTO> getList(int page) {
        /**
         * 게시물을 역순으로 조회하기 위해서 PageRequest.of 메소드의 세번째 파라미터로 Sort 객체 전달해야함
         * Sort.Order 객체로 구성된 리스트에 Sort.Order 객체를 추가
         * Sort.by(소트 리스트)로 소트 객체 생성 가능
         * Sort.Order.desc("createDate")는 작성일시를 역순으로 조회하기
         */


        Pageable pageable = PageRequest.of(page,15);
        System.out.println("pageable = " + pageable);
        QueryResults<QuestionEntity> queryResults = questionRepository.getAll(pageable);
        Long totalCount = queryResults.getTotal();

        List<QuestionDTO> questionDTOList = queryResults.getResults()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(questionDTOList, pageable, totalCount);

    }

    public QuestionEntity toEntity(QuestionDTO questionDTO) {
        List<AnswerEntity> answerList = new ArrayList<>();

        for (QuestionDTO.Answer answer : questionDTO.getAnswerList()) {
            AnswerEntity answerEntity = AnswerEntity.builder()
                    .id(answer.getId())
                    .content(answer.getContent())
                    .createDateTime(answer.getCreateDateTime())
                    .modifyDateTime(answer.getModifyDateTime())
                    .author(answer.getAuthor().toEntity())
                    .build();
            answerList.add(answerEntity);
        }



        return QuestionEntity.builder()
                .id(questionDTO.getId())
                .subject(questionDTO.getSubject())
                .content(questionDTO.getContent())
                .view(questionDTO.getView())
                .createDateTime(questionDTO.getCreateDateTime())
                .modifyDateTime(questionDTO.getModifyDateTime())
                .answerList(answerList)
                .author(questionDTO.getAuthor().toEntity())
                .build();
    }

    public QuestionDTO toDTO(QuestionEntity questionEntity) {
        List<QuestionDTO.Answer> answerList = new ArrayList<>();

        for (AnswerEntity answerEntity : questionEntity.getAnswerList()) {
            QuestionDTO.Answer answer = new QuestionDTO.Answer();
            answer.setId(answerEntity.getId());
            answer.setAuthor(answerEntity.getAuthor().toDTO());
            answer.setContent(answerEntity.getContent());
            answer.setRecommend(recommendService.getAnswerRecommendCount(answer.getId()));
            answer.setCreateDateTime(answerEntity.getCreateDateTime());
            answer.setModifyDateTime(answerEntity.getModifyDateTime());

            answerList.add(answer);
        }


        return QuestionDTO.builder()
                .id(questionEntity.getId())
                .subject(questionEntity.getSubject())
                .content(questionEntity.getContent())
                .recommend(recommendService.getQuestionRecommendCount(questionEntity.getId()))
                .view(questionEntity.getView())
                .createDateTime(questionEntity.getCreateDateTime())
                .modifyDateTime(questionEntity.getModifyDateTime())
                .answerList(answerList)
                .author(questionEntity.getAuthor().toDTO())
                .build();
    }

    public void recommend(SiteUserDTO siteUserDTO, QuestionDTO questionDTO){
        recommendService.recommend(siteUserDTO.toEntity(), this.toEntity(questionDTO));
    }

    @Override
    public void increaseView(QuestionDTO questionDTO) {
        questionDTO.setView(questionDTO.getView() + 1);
        this.questionRepository.save(this.toEntity(questionDTO));
    }

    @Override
    public Page<QuestionDTO> getSearchList(String keywords, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);

        QueryResults<QuestionEntity> queryResults = questionRepository.getQuestions(keywords, pageable);
        Long totalCount = queryResults.getTotal();

        List<QuestionDTO> questionDTOList = queryResults.getResults()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        PageImpl<QuestionDTO> page1 = new PageImpl<>(questionDTOList, pageable, totalCount);
        return page1;
    }
}
