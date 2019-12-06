package com.yyn.service;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-27-16:42
*/


import com.yyn.entity.Zhuanji;

import java.util.List;

public interface ZhuanjiService {
    List<Zhuanji> getAll(Integer start, Integer page);
    List<Zhuanji> All();
    Integer getTotal();
    Integer getTolPage(Integer rows);
    void add(Zhuanji Zhuanji);
    void update(Zhuanji Zhuanji);
    void delete(String id);
}
