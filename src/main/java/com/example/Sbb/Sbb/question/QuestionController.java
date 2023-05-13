package com.example.Sbb.Sbb.question;

import com.example.Sbb.Sbb.user.SiteUserDTO;
import com.example.Sbb.Sbb.answer.AnswerForm;
import com.example.Sbb.Sbb.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


/**
 * RequiredArgsConstructor는 questionRepository 속성을 포함하는 생성자 생성하고,
 * 롬복에서 제공하는 애너테이션으로 final이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 역할
 */

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionServiceImpl questionService;
    private final UserServiceImpl userService;


    /**
     * http://localhost:8080/question/list?page=0처럼 GET방식으로 요청된 URL에서 page 값을 가져오기 위해서
     * @RequestParam(value = "page", defaultValue = "0") int page 사용
     * 디폴트값은 0(첫 페이지 보여줌)
     */
    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {


        /**
         *질문목록 데이터를 생성하여 Model객체에 questionList라는 이름으로 값 저장
         *Model 객체는 자바 클래스와 템플릿 간의 연결고리를 한다.
         *Model 객체에 값을 담아두면 템플릿에서 그 값을 사용할 수 있다.
         */
        //List<QuestionEntity> questionEntityList = this.questionService.getList();
        //model.addAttribute("questionEntityList", questionEntityList);


        /**
         * 기존에 사용하던 questionEntityList를 보내던 방식에서 paging을 보내는 방식으로 변경
         * 따라서, 템플릿 엔진도 바꿔주어야 함
         */
        Page<QuestionEntity> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);



        return "question_list";
    }

    /**
     * 변하는 id값을 얻을 때 @PathVariable애너테이션 사용,
     * @RequestMapping(value = "/question/detail/{id}")에서 사용한 id와
     * @PathVariable("id")의 매개변수 이름이 동일해야함
     */
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        QuestionDTO questionDTO = this.questionService.getQuestion(id);
        model.addAttribute("question", questionDTO);
        return "question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String createQuestion(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        SiteUserDTO siteUserDTO = this.userService.getUser(principal.getName());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUserDTO);
        return "redirect:/question/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String createQuestion(QuestionForm questionForm){
        return "question_form";
    }

}
