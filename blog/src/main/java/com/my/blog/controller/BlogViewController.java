package com.my.blog.controller;

import com.my.blog.controller.response.ArticleViewResponse;
import com.my.blog.domain.Article;
import com.my.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class BlogViewController {
    private final ArticleService articleService;

    @GetMapping("/new")  // /new?id=1
    public String newArticle(@RequestParam(required = false) Long id, Model model) {

        // id 가 있으면 수정
        if( id != null){
            Article article = articleService.getArticleById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }else{
            model.addAttribute("article", new ArticleViewResponse());
        }
        // id가 없으면 생성
        return "newArticle";
    }

}
