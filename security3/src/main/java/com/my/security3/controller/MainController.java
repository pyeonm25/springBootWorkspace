package com.my.security3.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/")
    public @ResponseBody String mainPage(){
        return "main page";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}
