package com.example.Sbb.Sbb.question;


import com.example.Sbb.Sbb.user.SiteUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface QuestionService {
//    public List<QuestionEntity> getList();

    public QuestionDTO getQuestion(Long id);
    public void create(String subject, String content, SiteUserDTO siteUserDTO);
    public void modify(QuestionDTO questionDTO, String subject, String content);
    public Page<QuestionDTO> getList(int page);
    public Page<QuestionDTO> getSearchList(String keywords, int page, int size);
    public void delete(QuestionDTO questionDTO);
    public QuestionEntity toEntity(QuestionDTO questionDTO);
    public QuestionDTO toDTO(QuestionEntity questionEntity);

    public void recommend(SiteUserDTO siteUserDTO, QuestionDTO questionDTO);

}
