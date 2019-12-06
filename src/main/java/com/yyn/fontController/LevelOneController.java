package com.yyn.fontController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-04-11:05
*/

import com.yyn.dao.ArticleDao;
import com.yyn.dao.PictureDao;
import com.yyn.dao.ZhuanjiDao;
import com.yyn.entity.Article;
import com.yyn.entity.Picture;
import com.yyn.entity.Zhuanji;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("lo")
public class LevelOneController {
    @Autowired
    private PictureDao pictureDao;
    @Autowired
    private ZhuanjiDao zhuanjiDao;
    @Autowired
    private ArticleDao articleDao;

    @RequestMapping("get")
    public Map get(String uid,String type,String sub_type){
        Map<String,Object> map = new HashMap<>();
        List<Picture> pictures = pictureDao.selectAll();
        List<Zhuanji> zhuanjis = zhuanjiDao.selectAll();
        List<Article> articles = articleDao.selectAll();
        if ("all".equals(type)){
            map.put("status","200");
            map.put("head",pictures);
            map.put("albums",zhuanjis);
            map.put("articles",articles);
        }else if ("wen".equals(type)){
            map.put("albums",zhuanjis);
            map.put("status","200");
        }else if ("si".equals(type)){
            if ("ssyj".equals(sub_type)){
                List<Article> a = articleDao.getA(uid);
                map.put("articles",a);
                map.put("status","200");
            }else if ("xmfy".equals(sub_type)){
                map.put("articles",articles);
                map.put("status","200");
            }
        }
        return map;
    }
}
