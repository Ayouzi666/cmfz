package com.yyn.service;/*
   
   @author yyn
   @version 1.8
   @create 2019-11-26-18:41
*/

import com.yyn.entity.Picture;

import java.util.List;

public interface PictureService {
    List<Picture> getAll(Integer start,Integer page);
    Integer getTotal();
    Integer getTolPage(Integer rows);
    void add(Picture picture);
    void update(Picture picture);
    void delete(String id);
}
