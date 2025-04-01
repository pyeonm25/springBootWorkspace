package com.basic.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

// 컨트롤러로 들어오는 여러가지 요청값

// 무조건 HttpMessageConverter 로 응답
@Slf4j  // log 출력하게 하는 lombok 기능
@RestController   // Controller + ResponseBody
@RequestMapping("/test")
public class TestController {
    @GetMapping("")
    public String test() {
        System.out.println("test 111");
        log.trace("trace 2222");
        log.debug("debug 3333");
        log.info("test 4444");
        log.warn("test 5555");
        log.error("test 6666");

        return "test";
    }

    @GetMapping("/param")  // http://localhost:8081/test/param?name=개똥이&number=10
    public String testParam(@RequestParam("name") String name, @RequestParam(value = "number",required = false , defaultValue = "10") String number) {
        return "test param name = " + name + " number = " + number;
    }
    // http://localhost:8081/test/body/
    @PostMapping("/body")   // {"name" : "개똥이" ,"number" : 10 }
    public String testBody(@RequestBody @ModelAttribute Test test) {  // body -> raw -> json
        return "test body name = " + test.getName() + " number = " + test.getNumber();
    }
    // http://localhost:8081/test/개똥이/10
    @GetMapping("/{name}/{number}")
    public String test(@PathVariable String name, @PathVariable String number) {
        return "test path value" + name + " number = " + number;
    }

    public static class Test {
        String name;
        int number;

        public Test(String name, int number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public int getNumber() {
            return number;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
