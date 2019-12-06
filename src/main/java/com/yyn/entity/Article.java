package com.yyn.entity;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-28-15:59
*/


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String tatle;
    private String content;
    private String author;
    @JsonFormat
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String picture;
    private String teacherid;
    private String status;
}
