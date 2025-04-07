package com.my.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/myPage")
public class UserController {

    @GetMapping({"/",""})
    public String myPage() {
        return "myPage";
    }
}
