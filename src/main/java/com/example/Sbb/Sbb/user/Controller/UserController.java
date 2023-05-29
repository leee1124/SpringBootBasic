package com.example.Sbb.Sbb.user.Controller;

import com.example.Sbb.Sbb.answer.Data.AnswerDTO;
import com.example.Sbb.Sbb.answer.Service.AnswerService;
import com.example.Sbb.Sbb.question.Data.QuestionDTO;
import com.example.Sbb.Sbb.question.Service.QuestionService;
import com.example.Sbb.Sbb.user.Data.SiteUserDTO;
import com.example.Sbb.Sbb.user.Form.UserCreateForm;
import com.example.Sbb.Sbb.user.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm){
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signup_form";
        }

        if(!userCreateForm.getPassword().equals(userCreateForm.getPasswordConfirm())){
            bindingResult.rejectValue("passwordConfirm", "passwordIncorrect",
                    "비밀번호가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            userService.create(userCreateForm.getUsername(),
                    userCreateForm.getEmail(), userCreateForm.getPassword());
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
        }catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "login_form";
    }

    @PreAuthorize("isAuthenticated")
    @GetMapping("/profile")
    public String getProfile(Model model,Principal principal
            ,@RequestParam(value = "questionPage", defaultValue = "0")int questionPage
            ,@RequestParam(value = "answerPage", defaultValue = "0")int answerPage                 ){
        SiteUserDTO siteUserDTO = this.userService.getUser(principal.getName());
        Page<QuestionDTO>  questions = this.questionService.getQuestions(questionPage,5,siteUserDTO);
        Page<AnswerDTO> answerDTOList = this.answerService.getAnswers(answerPage,5, siteUserDTO);

        model.addAttribute("user", siteUserDTO);
        model.addAttribute("questions", questions);
        model.addAttribute("answers", answerDTOList);


        return "myPage";
    }

}
