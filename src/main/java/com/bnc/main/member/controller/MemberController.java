package com.bnc.main.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/login")
    public String login(){
        return "/views/member/login";
    }

    @GetMapping ("/joinUserForm")
    public String joinHome(){
        return "/views/member/joinUser";
    }
}
