package com.basic.boot.controller;

import com.basic.boot.domain.request.UserRequest;
import com.basic.boot.domain.response.UserResponse;
import com.basic.boot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApiController {
    // 생성자 주입으로 객체 의존성 주입받아 온다!!
    private final UserService userService;

    @GetMapping({"","/"})
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> list = userService.findAllUser();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping({"","/"})
    public String addOneUser(@RequestBody UserRequest userRequest) {
        log.info("addOneUser: request= {} 이게 들어왔다 ", userRequest);
        return "ok";
    }

}
