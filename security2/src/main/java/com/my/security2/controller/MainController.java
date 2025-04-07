package com.my.security2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;

@RestController
public class MainController {
    @GetMapping({"/",""})
    public String mainPage() {
        return "main";
    }
    @GetMapping("/auth")
    @ResponseBody
    public String authPage() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities(); // 콜랙션 객체  => 인덱스 없어서
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator(); // iterator 변경해야지만이 안에 있는 값 접근 가능
        GrantedAuthority grantedAuthority = iterator.next();
        String role = grantedAuthority.getAuthority();

        String authStr = role + ":" + username;
        return authStr;
    }
}
