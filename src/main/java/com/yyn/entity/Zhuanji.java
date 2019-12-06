package com.yyn.entity;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-27-10:27
*/


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zhuanji implements Serializable {
    @Id
    @KeySql(sql = "select uuid()",order = ORDER.BEFORE)
    private String id;
    private String tatle;
    private String picture;
    private String author;
    private String boyin;
    private String jishu;
    private String jianjie;
    @JsonFormat
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String start;
}
