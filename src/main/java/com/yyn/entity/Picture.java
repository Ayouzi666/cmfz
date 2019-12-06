package com.yyn.entity;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-26-18:27
*/


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture implements Serializable {
    @Id
    @KeySql(sql = "select uuid()",order = ORDER.BEFORE)
    private String id;
    private String name;
    private String address;
    private String jianjie;
    private String link;
    private String status;
}
