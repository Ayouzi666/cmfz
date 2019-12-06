package com.yyn.service;/*
   
   @author yyn
   @version 1.8
   @create 2019-12-04-16:08
*/

import com.yyn.entity.Teacher;

import java.util.List;

public interface UtService {
    List<Teacher> getAll(String id);
}
