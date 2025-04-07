package com.my.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/myPage")
    public String myPage() {
        return "myPage";
    }
}
