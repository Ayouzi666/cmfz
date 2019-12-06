package com.yyn.fontController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-04-14:29
*/

import com.yyn.dao.CountDao;
import com.yyn.entity.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("count")
public class CountController {
    @Autowired
    private CountDao countDao;

    @RequestMapping("get")
    public Map get(String uid,String cid) {
        Map<String, Object> map = new HashMap<>();
        Count count = new Count();
        count.setCid(cid);
        List<Count> select = countDao.select(count);
        map.put("status","200");
        map.put("counters",select);
        return map;
    }

    @RequestMapping("add")
    public Map add(String uid,String cid,String title) {
        Map<String, Object> map = new HashMap<>();
        Count count = new Count();
        Count count1 = new Count();
        count.setCid(cid);
        count.setName(title);
        count.setDate(new Date());
        count.setCount(0);
        countDao.insertSelective(count);
        count1.setCid(cid);
        List<Count> select = countDao.select(count1);
        map.put("status","200");
        map.put("counters",select);
        return map;
    }

    @RequestMapping("update")
    public Map update(String uid,String id,String cid,Integer count) {
        Map<String, Object> map = new HashMap<>();
        Count coun = new Count();
        Count count1 = new Count();
        coun.setId(id);
        coun.setCount(count);
        countDao.updateByPrimaryKeySelective(coun);
        count1.setCid(cid);
        List<Count> select = countDao.select(count1);
        map.put("status","200");
        map.put("counters",select);
        return map;
    }

    @RequestMapping("delete")
    public Map delete(String uid,String id,String cid) {
        Map<String, Object> map = new HashMap<>();
        Count count = new Count();
        Count count1 = new Count();
        count.setId(id);
        countDao.delete(count);
        count1.setCid(cid);
        List<Count> select = countDao.select(count1);
        map.put("status","200");
        map.put("counters",select);
        return map;
    }
}
