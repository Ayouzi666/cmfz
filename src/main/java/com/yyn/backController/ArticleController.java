package com.yyn.backController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-28-16:22
*/

import com.yyn.dao.ArticleDao;
import com.yyn.entity.Article;
import com.yyn.service.ArticleService;
import com.yyn.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleDao articleDao;

    @RequestMapping("getAll")
    @ResponseBody
    public Map getAll(Integer rows, Integer page){
        Map<String,Object> req = new HashMap<>();
        List<Article> all = articleService.getAll((page - 1) * rows, rows);
        Integer count = articleService.getCount();
        Integer allPage = articleService.getAllPage(rows);
        req.put("page", page);
        req.put("total", allPage);
        req.put("records", count);
        req.put("rows", all);
        return req;
    }

    @RequestMapping("getOne")
    @ResponseBody
    public Article getOne(String id){
        Article article = articleDao.selectByPrimaryKey(id);
        return article;
    }

    @RequestMapping("update")
    public String update(Integer id, String tatle, String status, String content, MultipartFile file, HttpServletRequest request){
        System.out.println(file);
        System.out.println(id);
        HttpUtil.getHttpUrl(file,request,request.getSession(),"img");
        Article article = new Article();
        article.setId(id);
        article.setTatle(tatle);
        article.setStatus(status);
        article.setContent(content);
        article.setPicture(file.getOriginalFilename());
        articleService.update(article);
        return "redirect:/jsp/article.jsp";
    }

    @RequestMapping("add")
    public String add(String tatle,String status,String content, MultipartFile file, HttpServletRequest request){
        System.out.println(file);
        System.out.println(tatle);
        Article article = new Article();
        HttpUtil.getHttpUrl(file,request,request.getSession(),"img");
        article.setStatus(status);
        article.setContent(content);
        article.setTatle(tatle);
        article.setDate(new Date());
        article.setPicture(file.getOriginalFilename());
        articleService.add(article);
        return "redirect:/jsp/article.jsp";
    }

    @RequestMapping("edit")
    public void edit(String id){
        articleService.delete(id);
    }
}
