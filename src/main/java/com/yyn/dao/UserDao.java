package com.yyn.dao;/*
   
   @author yyn
   @version 1.8
   @create 2019-11-29-17:24
*/

import com.yyn.entity.MapVO;
import com.yyn.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User> {
    Integer getUserByTime(@Param("sex") String sex, @Param("i") Integer i);
    List<MapVO> getAddress();
}
