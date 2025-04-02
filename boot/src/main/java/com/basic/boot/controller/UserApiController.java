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
    @GetMapping("/{username}")
    public ResponseEntity<Map<String,Object>>  getUser(@PathVariable String username) {
        log.trace("get user: {}", username);
        Map<String,Object> response = new HashMap<>();
        try{
           UserResponse user = userService.findUserById(username);
           response.put("status", HttpStatus.OK.value());
           response.put("data", user);
           return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("status", HttpStatus.NOT_FOUND.value());  // 201
            response.put("message" ,"user not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

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
    @PutMapping({"","/"})    // 비밀번호가 일치할때만 유저 이메일 수정
    public ResponseEntity<Map<String,Object>> updateUser(@ModelAttribute UserRequest userRequest) {
        log.trace("request = {}" , userRequest);
        Map<String,Object> response = new HashMap<>();
        try{

            userService.updateUser(userRequest);
            response.put("status", HttpStatus.OK.value());
            response.put("message" ,"user updated successfully");
            return ResponseEntity.ok(response);

        }catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());  // 201
            response.put("message" ,e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    // 이름이랑 비번이 일치하면 삭제  => 보통은 우리가 버튼 눌러서 비동기로 삭제
    @DeleteMapping({"","/"})
    public ResponseEntity<Map<String,Object>> deleteUser(@RequestBody UserRequest userRequest) {
        log.trace("request = {}" , userRequest);
        Map<String,Object> response = new HashMap<>();
        try{

            userService.deleteUser(userRequest);
            response.put("status", HttpStatus.OK.value());
            response.put("message" ,"user deleted successfully");
            return ResponseEntity.ok(response);

        }catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message" ,"user deleted not successfully");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


}
