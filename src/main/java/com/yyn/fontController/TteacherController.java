package com.yyn.fontController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-04-15:13
*/


import com.yyn.dao.TeacherDao;
import com.yyn.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("tteacher")
public class TteacherController {
    @Autowired
    private TeacherDao teacherDao;

    @RequestMapping("get")
    public Map get(String uid){
        Map<String,Object> map = new HashMap<>();
        List<Teacher> teachers = teacherDao.selectAll();
        map.put("status","200");
        map.put("list",teachers);
        return map;
    }
}
