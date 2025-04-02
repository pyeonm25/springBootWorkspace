package com.my.blog.controller;

import com.my.blog.controller.response.ArticleListResponse;
import com.my.blog.controller.response.ArticleViewResponse;
import com.my.blog.domain.Article;
import com.my.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class BlogViewController {
    private final ArticleService articleService;

    @GetMapping("/new")  // /new?id=1
    public String newArticle(@RequestParam(required = false) Long id, Model model) {

        // id 가 있으면 수정
        try{
            Article article = articleService.getArticleById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }catch(Exception e){
            model.addAttribute("article", new ArticleViewResponse());
        }
        // id가 없으면 생성
        return "newArticle";
    }

    @GetMapping({"","/"})
    public String getAllArticles(Model model){
        List<ArticleListResponse> list = articleService.getAllArticleList();
        if(list.size() == 0){
            return "newArticle";
        }
        model.addAttribute("articles" , articleService.getAllArticleList());
        return "articleList";
    }

    // 2번째 게시글 보기  ==> templates/article.html 실행
    // localhost:8082/articles/2
    @GetMapping("/{id}")
    public String getOnearticle(@PathVariable Long id, Model model) {
       try {
           Article article = articleService.getArticleById(id);
           model.addAttribute("article", new ArticleViewResponse(article));
           return "article";
       }catch(Exception e){
           return "home";
       }

    }


}
