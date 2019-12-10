package com.yyn.entity;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-10-15:03
*/


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource {
    private String id;
    private String resource_name;
}
