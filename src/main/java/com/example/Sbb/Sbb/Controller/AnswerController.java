package com.example.Sbb.Sbb.Controller;

import com.example.Sbb.Sbb.Entity.QuestionEntity;
import com.example.Sbb.Sbb.Entity.SiteUserEntity;
import com.example.Sbb.Sbb.Form.AnswerForm;
import com.example.Sbb.Sbb.Service.AnswerService;
import com.example.Sbb.Sbb.Service.QuestionService;
import com.example.Sbb.Sbb.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/answer") //URL 프리픽스
@RequiredArgsConstructor
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;


    /*
     * RequestParam String Content 항목은 템플릿에서 답변으로 입력한 내용(Content)을 얻기 위해 추가
     * 템플릿의 답변 내용에 해당하는 textarea의 name속성명이 content이기 때문에 여기서도 변수명을 content로 사용해야 함
     * 만약 content 대신 다른 이름으로 사용하면 오류 발생
     * createAnswer 메서드의 URL 매핑 /create/{id}에서 {id}는 질문의 id이므로,
     * 이 id값으로 질문을 조회하고 없을 경우에는 404 오류 발생
     * @param principal: 현재 로그인한 사용자에 대해서 알기 위해서 사용
     */
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id,
                               @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal){
        QuestionEntity questionEntity = this.questionService.getQuestion(id);
        SiteUserEntity siteUserEntity = this.userService.getUser(principal.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("question", questionEntity);
            return "question_detail";
        }
        this.answerService.create(questionEntity, answerForm.getContent(), siteUserEntity);
        return String.format("redirect:/question/detail/%s", id);
    }
}
