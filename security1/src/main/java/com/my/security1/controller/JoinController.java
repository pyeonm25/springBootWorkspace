package com.my.security1.controller;

import com.my.security1.dto.JoinLoginRequest;
import com.my.security1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class JoinController {
    private final UserService userService;

    @GetMapping("/join")
    public String join() {
        return "join";
    }
    @PostMapping("/joinProc")
    public String joinProc(JoinLoginRequest request) {
        log.trace("request= {}" , request.getUsername() +" " + request.getPassword());
        try{
            userService.addUser(request);
        }catch (Exception e){
            return "redirect:/join";
        }


        return "redirect:/login";
    }

}
