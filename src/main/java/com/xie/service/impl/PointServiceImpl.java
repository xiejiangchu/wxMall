package com.xie.service.impl;

import com.xie.bean.Point;
import com.xie.dao.PointDao;
import com.xie.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-24 下午6:47
 */
@Service
public class PointServiceImpl implements PointService {

    @Autowired
    PointDao pointDao;

    @Override
    public Point getByUid(Integer uid) {
        return pointDao.getByUid(uid);
    }

    @Override
    public Point getById(int id) {
        return pointDao.getById(id);
    }

    @Override
    public int insert(Point point) {
        return pointDao.insert(point);
    }

    @Override
    public int update(Point point) {
        return pointDao.update(point);
    }

    @Override
    public int add(int uid, double money, int point) {
        return pointDao.add(uid, money, point);
    }

    @Override
    public int delete(Point point) {
        return pointDao.delete(point);
    }

    @Override
    public int delete(int id) {
        return pointDao.delete(id);
    }
}
