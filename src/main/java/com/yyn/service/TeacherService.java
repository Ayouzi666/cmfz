package com.yyn.service;/*
   
   @author yyn
   @version 1.8
   @create 2019-12-01-15:28
*/

import com.yyn.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAll(Integer start, Integer rows);
    void add(Teacher teacher);
    void delete(String id);
    void update(Teacher teacher);
    Integer getCount();
    Integer getPage(Integer rows);
}
