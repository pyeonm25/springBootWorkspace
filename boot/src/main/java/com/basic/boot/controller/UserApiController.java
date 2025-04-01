package com.basic.boot.controller;

import com.basic.boot.domain.response.UserResponse;
import com.basic.boot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApiController {
    // 생성자 주입으로 객체 의존성 주입받아 온다!!
    private final UserService userService;

    @GetMapping({"","/"})
    public List<UserResponse> getAllUsers() {
        List<UserResponse> list = userService.findAllUser();
        return list;
    }

}
