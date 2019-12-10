package com.yyn.entity;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-10-15:02
*/


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private String id;
    private String role_name;
    private List<Resource> resources;
}
