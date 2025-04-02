package com.my.blog.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class UpdateArticleRequest {
    private String title;
    private String content;
}
