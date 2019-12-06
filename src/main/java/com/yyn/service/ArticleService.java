package com.yyn.service;/*
   
   @author yyn
   @version 1.8
   @create 2019-11-28-16:06
*/

import com.yyn.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAll(Integer start,Integer rows);
    void add(Article article);
    void delete(String id);
    void update(Article article);
    Integer getCount();
    Integer getAllPage(Integer rows);
    List<Article> getA(String uid);
}
