package com.my.blog.service;

import com.my.blog.controller.request.AddArticleRequest;
import com.my.blog.domain.Article;
import com.my.blog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor  // 생성자 주입  => new ArticleRepository()
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional
    public Article save(AddArticleRequest request) {
        return articleRepository.save(request.toEntity());
    }
}
