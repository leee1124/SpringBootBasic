package com.example.Sbb.Sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @RequestMapping("/sbb")
    @ResponseBody
    public String index(){
        return "안녕하세요. Sbb에 오신것을 환영합니다.";
    }

    @RequestMapping("/")
    public String root(){
        return "redirect:/question/list";
    }
}
