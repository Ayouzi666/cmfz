package com.yyn.service;/*
   
   @author yyn
   @version 1.8
   @create 2019-11-29-17:25
*/

import com.yyn.entity.MapVO;
import com.yyn.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll(Integer start,Integer rows);
    void add(User user);
    void delete(String id);
    void update(User user);
    Integer getCount();
    Integer getPage(Integer rows);
    Integer getUserByTime( String sex, Integer i);
    List<MapVO> getAddress();
}
