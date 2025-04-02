package com.my.blog.service;

import com.my.blog.controller.request.AddArticleRequest;
import com.my.blog.controller.request.UpdateArticleRequest;
import com.my.blog.controller.response.ArticleListResponse;
import com.my.blog.controller.response.ArticleResponse;
import com.my.blog.domain.Article;
import com.my.blog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor  // 생성자 주입  => new ArticleRepository()
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article getArticleById(Long id){
        Article article = articleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("not found id : " + id)
        );
        return article;
    }

    public List<ArticleListResponse> getAllArticleList(){
        List<Article> articleList = articleRepository.findAll();
        return articleList.stream().map(ArticleListResponse::new).collect(Collectors.toList());
    }


    public List<ArticleResponse> findAllArticle() {
        List<Article> articles = articleRepository.findAll();
//        List<ArticleResponse> list = new ArrayList<>();
//        for (Article article : articles) {
//            list.add(new ArticleResponse(article));
//        }
//        return list;
//        return articles.stream().map((article)-> new ArticleResponse(article))
//                .collect(Collectors.toList());
        return articles.stream().map(ArticleResponse::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public Article save(AddArticleRequest request) {
        return articleRepository.save(request.toEntity());
    }
    public ArticleResponse FindById(Long id) throws Exception{
        Article article = articleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("not found id : " + id)
        );
        return new ArticleResponse(article);
    }
    @Transactional
    public void UpdateArticle(Long id, UpdateArticleRequest request) throws Exception{
        Article article = articleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("not found id : " + id)
        );
        article.update(request);
        articleRepository.save(article);
    }
    @Transactional
    public void DeleteArticle(Long id) throws Exception{
        Article article = articleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("not found id : " + id)
        );
        articleRepository.delete(article);
    }
}
