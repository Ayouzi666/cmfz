package com.yyn.entity;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-29-16:53
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
public class User implements Serializable {
    @Id
    @KeySql(sql = "select uuid()",order = ORDER.BEFORE)
    private String id;
    private String phone;
    private String password;
    private String salt;
    private String status;
    private String username;
    private String nickname;
    private String sex;
    private String sign;
    private String head;
    private String address;
    @JsonFormat
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
