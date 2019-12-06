package com.yyn.service;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-04-16:08
*/


import com.yyn.dao.UtDao;
import com.yyn.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UtServiceImpl implements UtService{
    @Autowired
    private UtDao utDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Teacher> getAll(String id) {
        List<Teacher> all = utDao.getAll(id);
        return all;
    }
}
