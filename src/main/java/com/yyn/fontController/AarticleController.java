package com.yyn.fontController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-04-11:21
*/

import com.yyn.dao.ArticleDao;
import com.yyn.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("aarticle")
public class AarticleController {
    @Autowired
    private ArticleDao articleDao;

    @RequestMapping("get")
    public Map get(String uid,String id){
        Map<String,Object> map = new HashMap<>();
        Article article = articleDao.selectByPrimaryKey(id);
        map.put("status","200");
        map.put("article",article);
        return map;
    }
}
