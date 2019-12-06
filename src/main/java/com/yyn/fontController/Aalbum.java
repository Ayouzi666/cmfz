package com.yyn.fontController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-04-11:28
*/

import com.yyn.dao.ZhuanjiDao;
import com.yyn.entity.Zhangjie;
import com.yyn.entity.Zhuanji;
import com.yyn.service.ZhangjieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("aalbum")
public class Aalbum {
    @Autowired
    private ZhuanjiDao zhuanjiDao;
    @Autowired
    private ZhangjieService zhangjieService;

    @RequestMapping("get")
    public Map get(String uid,String id){
        Map<String,Object> map = new HashMap<>();
        Zhuanji zhuanji = zhuanjiDao.selectByPrimaryKey(id);
        Integer total = zhangjieService.getTotal(id);
        List<Zhangjie> all = zhangjieService.getAll(0, total, id);
        map.put("status","200");
        map.put("ablum",zhuanji);
        map.put("list",all);
        return map;
    }
}
