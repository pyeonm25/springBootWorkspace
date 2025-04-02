package com.my.blog.domain;

import com.my.blog.controller.request.UpdateArticleRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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
