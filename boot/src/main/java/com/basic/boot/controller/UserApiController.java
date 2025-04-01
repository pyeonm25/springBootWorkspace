package com.basic.boot.controller;

import com.basic.boot.domain.request.UserRequest;
import com.basic.boot.domain.response.UserResponse;
import com.basic.boot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String,Object>> addOneUser(@RequestBody UserRequest userRequest) {
        log.trace("addOneUser: request= {} 이게 들어왔다 ", userRequest);
        Map<String,Object> response = new HashMap<>();
       try {
           userService.saveUser(userRequest);
            response.put("status", HttpStatus.CREATED.value());  // 201
            response.put("message" ,"user created successfully");
            return ResponseEntity.ok(response);
       }catch (Exception e) {
           response.put("status", HttpStatus.BAD_REQUEST.value());  // 201
           response.put("message" ,e.getMessage());
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
       }

    }

}
