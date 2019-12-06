package com.yyn.service;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-29-17:28
*/

import com.yyn.dao.UserDao;
import com.yyn.entity.MapVO;
import com.yyn.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> getAll(Integer start, Integer rows) {
        List<User> users = userDao.selectByRowBounds(new User(), new RowBounds(start, rows));
        return users;
    }

    @Override
    public void add(User user) {
        userDao.insertSelective(user);
    }

    @Override
    public void delete(String id) {
        userDao.deleteByPrimaryKey(id);
    }

    @Override
    public void update(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getCount() {
        int i = userDao.selectCount(new User());
        return i;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getPage(Integer rows) {
        int i = userDao.selectCount(new User());
        if(i%rows==0){
            i=i/rows;
        }else {
            i=i/rows+1;
        }
        return i;
    }

    @Override
    public Integer getUserByTime(String sex, Integer i) {
        Integer userByTime = userDao.getUserByTime(sex, i);
        return userByTime;
    }

    @Override
    public List<MapVO> getAddress() {
        List<MapVO> address = userDao.getAddress();
        return address;
    }
}
