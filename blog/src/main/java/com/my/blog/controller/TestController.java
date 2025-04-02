package com.my.blog.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {
    @GetMapping("/example")
    public String example(Model model) {

        Person person = new Person(1L, "개똥이",10, List.of("영화","요리"));
        model.addAttribute("person", person);
        return "test/example";  // temeples/ 파일이름 .html
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class Person{
        private Long id;
        private String name;
        private Integer age;
        private List<String> hobbies;

    }
}
