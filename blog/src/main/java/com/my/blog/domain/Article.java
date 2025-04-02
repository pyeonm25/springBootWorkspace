package com.my.blog.domain;

import com.my.blog.controller.request.UpdateArticleRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincreament 추가
    @Column(name="id" , updatable = false)
    private Long articleId;
    @Column(name="title" , nullable = false)
    private String title;
    @Column(name="content" , nullable = false)
    private String content;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(UpdateArticleRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}
