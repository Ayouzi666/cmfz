package com.yyn.dao;/*
   
   @author yyn
   @version 1.8
   @create 2019-12-04-15:59
*/

import com.yyn.entity.Teacher;
import com.yyn.entity.Ut;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UtDao extends Mapper<Ut> {
    List<Teacher> getAll(String id);
}
