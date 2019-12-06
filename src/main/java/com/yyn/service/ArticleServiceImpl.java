package com.yyn.service;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-28-16:14
*/

import com.yyn.dao.ArticleDao;
import com.yyn.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleDao articleDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Article> getAll(Integer start, Integer rows) {
        List<Article> articles = articleDao.selectByRowBounds(new Article(), new RowBounds(start, rows));
        return articles;
    }

    @Override
    public void add(Article article) {
        articleDao.insertSelective(article);
    }

    @Override
    public void delete(String id) {
        articleDao.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Article article) {
        articleDao.updateByPrimaryKeySelective(article);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getCount() {
        int i = articleDao.selectCount(new Article());
        return i;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getAllPage(Integer rows) {
        int i = articleDao.selectCount(new Article());
        if (i%rows==0){
            i = i/rows;
        }else {
            i=i/rows+1;
        }
        return i;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Article> getA(String uid) {
        List<Article> a = articleDao.getA(uid);
        return a;
    }
}
