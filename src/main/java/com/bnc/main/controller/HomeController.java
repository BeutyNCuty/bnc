package com.bnc.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}
