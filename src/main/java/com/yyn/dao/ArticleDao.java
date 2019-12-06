package com.yyn.dao;/*
   
   @author yyn
   @version 1.8
   @create 2019-11-28-16:04
*/

import com.yyn.entity.Article;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleDao extends Mapper<Article> {
    List<Article> getA(String uid);
}
