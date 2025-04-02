package com.my.blog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access= AccessLevel.PROTECTED)
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
}
