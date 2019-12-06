package com.yyn.entity;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-04-14:31
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Count {
    @Id
    @KeySql(sql = "select uuid()",order = ORDER.BEFORE)
    private String id;
    private String name;
    private Integer count;
    @JsonFormat
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String cid;
}
