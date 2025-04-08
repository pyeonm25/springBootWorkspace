package com.my.studyrecordsecure.controller;

import com.my.studyrecordsecure.config.auth.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
@Slf4j
@Controller
public class HomeController {
    @GetMapping({"","/" , "/home"})
    public String home() {
        return "home";
    }
    @GetMapping("/login-form")
    public String login() {

        //return "loginForm2";
         return "loginForm";
    }
    @GetMapping({"/sign-up"})
    public String signUp() {
        //return "member/joinForm2";
        return "member/joinForm";

    }

    @GetMapping("/oauth")
    public @ResponseBody OAuth2User testLogin(Authentication authentication,
                                              @AuthenticationPrincipal OAuth2User oauth){
        if(authentication == null){
            return null;
        }
        OAuth2User oAuth2User2 = (OAuth2User)authentication.getPrincipal();
        log.trace("oauth = {}" ,oauth);
        return oAuth2User2;
    }



    @GetMapping("/error")
    public String handleError(@RequestParam(value = "message", required = false) String message, Model model) {
        log.error(message);
        if (message != null) {
            model.addAttribute("errorMessage", message);
        } else {
            model.addAttribute("errorMessage", "알 수 없는 오류가 발생했습니다.");
        }
        return "error";
    }
    @GetMapping("/test")
    @ResponseBody
    public CustomUserDetails test(@AuthenticationPrincipal CustomUserDetails  customUserDetails){
        if(customUserDetails== null){
            return null;
        }
        return customUserDetails;
    }

    // csrf 적용하면 넣어줘야함
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }

}
