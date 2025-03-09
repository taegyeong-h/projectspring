package com.example.projectspring.controller;

import jdk.jfr.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {


    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "홍태경");
        return "greetings";   //template/greetings.mustache -> 브라우저로 전송
    }

    @GetMapping("bye")
    public String SeeYouNect(Model model) {
        model.addAttribute("nickname", "홍태경");
        return "goodbye";
    }

}
