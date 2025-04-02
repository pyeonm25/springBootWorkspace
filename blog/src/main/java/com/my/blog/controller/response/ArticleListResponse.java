package com.my.blog.controller.response;

import com.my.blog.domain.Article;
import lombok.Getter;

@Getter
public class ArticleListResponse {
    private Long id;
    private String title;
    private String content;

    public ArticleListResponse(Article article) {
        this.id = article.getArticleId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }

}
