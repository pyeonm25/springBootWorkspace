package com.my.blog.controller;

import com.my.blog.domain.Article;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/articles")
@RestController   // @ResponseBody
public class BlogApiController {

    @PostMapping({"","/"})
    public String addArticle(@RequestBody Article article) {
          return null;
    }
}
