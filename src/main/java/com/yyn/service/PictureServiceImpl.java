package com.yyn.service;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-26-18:56
*/


import com.yyn.dao.PictureDao;
import com.yyn.entity.Picture;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PictureServiceImpl implements PictureService{
    @Autowired
    private PictureDao pictureDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Picture> getAll(Integer start, Integer page) {
        List<Picture> pictures = pictureDao.selectByRowBounds(new Picture(), new RowBounds(start, page));
        return pictures;
    }

    @Override
    public Integer getTotal() {
        int size = pictureDao.selectAll().size();
        return size;
    }

    @Override
    public Integer getTolPage(Integer rows) {
        int size = pictureDao.selectAll().size();
        if (size%rows==0){
            return size/rows;
        }else {
            return size/rows+1;
        }

    }

    @Override
    public void add(Picture picture) {
        pictureDao.insertSelective(picture);
    }

    @Override
    public void update(Picture picture) {
        pictureDao.updateByPrimaryKeySelective(picture);
    }

    @Override
    public void delete(String id) {
        pictureDao.deleteByPrimaryKey(id);
    }
}
