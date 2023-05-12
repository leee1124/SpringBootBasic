package com.example.Sbb.Sbb.answer;

import com.example.Sbb.Sbb.question.QuestionDTO;
import com.example.Sbb.Sbb.user.SiteUserDTO;
import com.example.Sbb.Sbb.question.QuestionService;
import com.example.Sbb.Sbb.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
     * 현재 로그인한 사용자 정보를 알기 위해서는 Principal 객체를 사용해야 함
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id,
                               @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal){
        QuestionDTO questionDTO = this.questionService.getQuestion(id);
        SiteUserDTO siteUserDTO = this.userService.getUser(principal.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("question", questionDTO);
            return "question_detail";
        }
        this.answerService.create(questionDTO, answerForm.getContent(), siteUserDTO);
        return String.format("redirect:/question/detail/%s", id);
    }
}
