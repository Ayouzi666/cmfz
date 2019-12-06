package com.yyn.dao;/*
   
   @author yyn
   @version 1.8
   @create 2019-11-27-17:50
*/

import com.yyn.entity.Zhangjie;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZhangjieDao extends Mapper<Zhangjie> {
    List<Zhangjie> getAll(Integer start,Integer rows,String belong);
    Integer getTotal(String belong);
}
