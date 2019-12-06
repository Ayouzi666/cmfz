package com.yyn.fontController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-04-11:46
*/

import com.yyn.dao.ClazzDao;
import com.yyn.entity.Clazz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("clazz")
public class ClazzController {
    @Autowired
    private ClazzDao clazzDao;

    @RequestMapping("get")
    public Map get(String uid){
        Map<String,Object> map = new HashMap<>();
        Clazz clazz = new Clazz();
        clazz.setUid(uid);
        List<Clazz> select = clazzDao.select(clazz);
        map.put("status","200");
        map.put("option",select);
        return map;
    }

    @RequestMapping("add")
    public Map add(String uid,String title){
        Map<String,Object> map = new HashMap<>();
        Clazz clazz = new Clazz();
        Clazz clazz1 = new Clazz();
        clazz.setName(title);
        clazz.setUid(uid);
        clazzDao.insertSelective(clazz);
        clazz1.setUid(uid);
        List<Clazz> select = clazzDao.select(clazz1);
        map.put("status","200");
        map.put("option",select);
        return map;
    }

    @RequestMapping("delete")
    public Map delete(String uid,String id){
        Map<String,Object> map = new HashMap<>();
        Clazz clazz = new Clazz();
        Clazz clazz1 = new Clazz();
        clazz.setId(id);
        clazz.setUid(uid);
        clazzDao.delete(clazz);
        clazz1.setUid(uid);
        List<Clazz> select = clazzDao.select(clazz1);
        map.put("status","200");
        map.put("option",select);
        return map;
    }
}
