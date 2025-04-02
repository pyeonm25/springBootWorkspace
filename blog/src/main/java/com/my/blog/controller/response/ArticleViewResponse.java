package com.my.blog.controller.response;

import com.my.blog.domain.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createTime;
    public ArticleViewResponse(Article article) {
        this.id= article.getArticleId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createTime = article.getCreatedAt();
    }
}
