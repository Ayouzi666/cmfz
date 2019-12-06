package com.yyn.service;/*
   
   @author yyn
   @version 1.8
   @create 2019-11-27-18:36
*/

import com.yyn.entity.Zhangjie;

import java.util.List;

public interface ZhangjieService {
    List<Zhangjie> getAll(Integer start, Integer rows, String belong);
    void add(Zhangjie zhangjie);
    void delete(String id);
    void update(Zhangjie zhangjie);
    Integer getTotal(String belong);
    Integer getTolPage(String belong,Integer rows);
}
