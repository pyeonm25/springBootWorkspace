package com.my.blog.service;

import com.my.blog.controller.request.AddArticleRequest;
import com.my.blog.domain.Article;
import com.my.blog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor  // 생성자 주입  => new ArticleRepository()
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article save(AddArticleRequest request) {
        return articleRepository.save(request.toEntity());
    }
}
