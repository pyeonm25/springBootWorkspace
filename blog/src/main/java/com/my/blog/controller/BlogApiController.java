package com.my.blog.controller;

import com.my.blog.controller.request.AddArticleRequest;
import com.my.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/api/articles")
@RestController   // @ResponseBody
@RequiredArgsConstructor
public class BlogApiController {

    private final ArticleService articleService;

    // 아티클 값 1개 가져오는것
    @GetMapping("/{id}")
    public ResponseEntity getArticles(@PathVariable Long id) {
        return null;
    }
    @PostMapping({"","/"})
    public ResponseEntity<Map<String,Object>> addArticle(@RequestBody AddArticleRequest request) {
          Map<String , Object> response = new HashMap<>();
        if(articleService.save(request) == null){
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "article save failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "article save success");

        return ResponseEntity.ok(response);
    }
}
