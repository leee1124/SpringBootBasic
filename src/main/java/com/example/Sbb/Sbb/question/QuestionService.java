package com.example.Sbb.Sbb.question;


import com.example.Sbb.Sbb.user.SiteUserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionService {
    public List<QuestionEntity> getList();
    public QuestionDTO getQuestion(Integer id);
    public void create(String subject, String content, SiteUserDTO siteUserDTO);
    public void modify(QuestionDTO questionDTO, String subject, String content);
    public Page<QuestionEntity> getList(int page);
    public void delete(QuestionDTO questionDTO);
    public QuestionEntity toEntity(QuestionDTO questionDTO);
    public QuestionDTO toDTO(QuestionEntity questionEntity);

    public void recommend(QuestionDTO questionDTO, SiteUserDTO siteUserDTO);

}
