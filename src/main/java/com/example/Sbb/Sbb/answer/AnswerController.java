package com.example.Sbb.Sbb.answer;

import com.example.Sbb.Sbb.question.QuestionDTO;
import com.example.Sbb.Sbb.question.QuestionForm;
import com.example.Sbb.Sbb.question.QuestionService;
import com.example.Sbb.Sbb.user.SiteUserDTO;
import com.example.Sbb.Sbb.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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


    /**
     * Answer로 HTTP POST를 보내기 전에 GET으로 answer_form 접속해야함.
     * answer_form에서 질문을 수정한 후 POST를 보냄
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modifyAnswer(AnswerForm answerForm, @PathVariable("id") Integer id, Principal principal){
        AnswerDTO answerDTO = this.answerService.getAnswer(id);
        if(!answerDTO.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerForm.setContent(answerDTO.getContent());

        return "answer_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modifyQuestion(@Valid AnswerForm answerForm, BindingResult bindingResult,
                                 @PathVariable("id")Integer id, Principal principal){
        if(bindingResult.hasErrors()){
            return "answer_form";
        }

        AnswerDTO answerDTO = this.answerService.getAnswer(id);

        if(!answerDTO.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.answerService.modify(answerDTO, answerForm.getContent());
        return String.format("redirect:/question/detail/%s", answerDTO.getQuestion().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String deleteAnswer(Principal principal, @PathVariable("id") Integer id){
        AnswerDTO answerDTO = this.answerService.getAnswer(id);
        if(!answerDTO.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.answerService.delete(answerDTO);
        return String.format("redirect:/question/detail/%s", answerDTO.getQuestion().getId());
    }
}
