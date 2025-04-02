package com.my.blog.controller;

import com.my.blog.controller.request.AddArticleRequest;
import com.my.blog.controller.request.UpdateArticleRequest;
import com.my.blog.controller.response.ArticleResponse;
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
    public ResponseEntity getOneArticle(@PathVariable Long id) {

        try{
            ArticleResponse response = articleService.FindById(id);
            return new ResponseEntity(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

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

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")  // id를 입력 받아서 id가 있으면 제목 내욜 수정
    public ResponseEntity updateArticle(@PathVariable Long id,
                                        @RequestBody UpdateArticleRequest request) {
        log.trace("id = {} , request ={}" , id, request);

        try{
            if(request.getContent().isBlank() || request.getContent().isBlank()){
                throw new Exception(" value is required");
            }
            articleService.UpdateArticle(id, request);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("article updated successfully",HttpStatus.OK);
    }
    @DeleteMapping("/{id}")  // id를 입력 받아서 id가 있으면 삭제
    public ResponseEntity DeleteArticle(){
        return null;
    }
}
