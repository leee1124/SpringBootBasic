package com.example.Sbb.Sbb.question;

import com.example.Sbb.Sbb.DataNotFoundException;
import com.example.Sbb.Sbb.user.SiteUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public void create(String subject, String content, SiteUserEntity author){
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setSubject(subject);
        questionEntity.setContent(content);
        questionEntity.setCreateDateTime(LocalDateTime.now());
        questionEntity.setAuthor(author);
        this.questionRepository.save(questionEntity);
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
}
