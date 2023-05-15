package com.example.Sbb.Sbb.question;

import com.example.Sbb.Sbb.user.SiteUserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionService {
    public List<QuestionEntity> getList();
    public QuestionDTO getQuestion(Integer id);
    public void create(String subject, String content, SiteUserDTO siteUserDTO);
    public Page<QuestionEntity> getList(int page);
    public void delete(QuestionDTO questionDTO);

}
