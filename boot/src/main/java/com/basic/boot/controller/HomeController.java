package com.basic.boot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 컨트롤러가 응답하는 여러가지 방법

@Controller// viewResolver 로 응답
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "home";  // ViewResovler -> 화면 -> view 템플릿 리턴 -> text/html
    }

    @GetMapping("/msg")
    @ResponseBody   // viewResolver 가 아니라 HttpMessageConverter
    public String msg(){
        return "hello~~";  // HttpMessageConverter -> String
    }
    @GetMapping("/obj")
    @ResponseBody
    public Test obj(){
        Test test = new Test("개똥이", 1001);
        return test; // HttpMessageConverter -> json
    }

    public static class Test{
        String name;
        int number;
        public Test(String name, int number){
            this.name = name;
            this.number = number;
        }
        public String getName() {
            return name;
        }
        public int getNumber() {return number;}
        public void setName(String name) {
            this.name = name;
        }
        public void setNumber(int number) {
            this.number = number;
        }
    }
}
