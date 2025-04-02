package com.my.blog.controller.request;

import com.my.blog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddArticleRequest {
    private String title;
    private String content;

    // 저장은 entity
    public Article toEntity(){
        //return new Article();
    }
}
