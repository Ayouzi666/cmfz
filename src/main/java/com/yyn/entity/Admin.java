package com.yyn.entity;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-26-11:45
*/


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {
    @Id
    // @KeySql 指定主键策略 1. uuid 2.自增 @KeySql(useGeneratedKeys = true)
    @KeySql(sql = "select uuid()", order = ORDER.BEFORE)
    private String id;
    private String username;
    private String password;
    @Transient
    private List<Role> roles;

}
