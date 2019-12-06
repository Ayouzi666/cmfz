package com.yyn.service;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-26-16:30
*/


import com.yyn.dao.AdminDao;
import com.yyn.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin login(Admin admin) {
        Admin admin1 = adminDao.selectOne(admin);
        return admin1;
    }
}
