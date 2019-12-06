package com.yyn.service;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-27-18:37
*/


import com.yyn.dao.ZhangjieDao;
import com.yyn.entity.Zhangjie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ZhangjieServiceImpl implements ZhangjieService{
    @Autowired
    private ZhangjieDao zhangjieDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Zhangjie> getAll(Integer start, Integer rows, String belong) {
        List<Zhangjie> all = zhangjieDao.getAll(start, rows, belong);
        return all;
    }

    @Override
    public void add(Zhangjie zhangjie) {
        zhangjieDao.insertSelective(zhangjie);
    }

    @Override
    public void delete(String id) {
        zhangjieDao.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Zhangjie zhangjie) {
        zhangjieDao.updateByPrimaryKeySelective(zhangjie);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getTotal(String belong) {
        Integer total = zhangjieDao.getTotal(belong);
        return total;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getTolPage(String belong, Integer rows) {
        Integer total = zhangjieDao.getTotal(belong);
        Integer i = 0;
        if (total%rows==0){
            i = total/rows;
        }else {
            i = total/rows+1;
        }
        return i;
    }
}
