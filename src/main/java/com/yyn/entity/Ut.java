package com.yyn.entity;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-04-15:51
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ut {
    @Id
    @KeySql(sql = "select uuid()",order = ORDER.BEFORE)
    private String id;
    private String uid;
    private String tid;
}
