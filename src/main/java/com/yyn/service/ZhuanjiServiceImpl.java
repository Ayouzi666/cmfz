package com.yyn.service;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-27-16:43
*/


import com.yyn.dao.ZhuanjiDao;
import com.yyn.entity.Zhuanji;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ZhuanjiServiceImpl implements ZhuanjiService{
    @Autowired
    private ZhuanjiDao zhuanjiDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Zhuanji> getAll(Integer start, Integer page) {
        List<Zhuanji> zhuanjis = zhuanjiDao.selectByRowBounds(new Zhuanji(), new RowBounds(start, page));
        return zhuanjis;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Zhuanji> All() {
        List<Zhuanji> zhuanjis = zhuanjiDao.selectAll();
        return zhuanjis;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getTotal() {
        int i = zhuanjiDao.selectCount(new Zhuanji());
        return i;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getTolPage(Integer rows) {
        int i = zhuanjiDao.selectCount(new Zhuanji());
        int a = 0;
        if (i%rows==0){
            a = i/rows;
        }else {
            a = i/rows+1;
        }
        return a;
    }

    @Override
    public void add(Zhuanji Zhuanji) {
        zhuanjiDao.insertSelective(Zhuanji);
    }

    @Override
    public void update(Zhuanji Zhuanji) {
        zhuanjiDao.updateByPrimaryKeySelective(Zhuanji);
    }

    @Override
    public void delete(String id) {
        zhuanjiDao.deleteByPrimaryKey(id);
    }
}
