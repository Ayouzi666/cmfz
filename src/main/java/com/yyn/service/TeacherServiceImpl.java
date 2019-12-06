package com.yyn.service;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-01-15:29
*/


import com.yyn.dao.TeacherDao;
import com.yyn.entity.Teacher;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherDao teacherDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Teacher> getAll(Integer start, Integer rows) {
        List<Teacher> teachers = teacherDao.selectByRowBounds(new Teacher(), new RowBounds(start, rows));
        return teachers;
    }

    @Override
    public void add(Teacher teacher) {
        teacherDao.insertSelective(teacher);
    }

    @Override
    public void delete(String id) {
        teacherDao.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Teacher teacher) {
        teacherDao.updateByPrimaryKeySelective(teacher);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getCount() {
        int i = teacherDao.selectCount(new Teacher());
        return i;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getPage(Integer rows) {
        int i = teacherDao.selectCount(new Teacher());
        if (i%rows==0){
            i=i/rows;
        }else {
            i=i/rows+1;
        }
        return i;
    }
}
