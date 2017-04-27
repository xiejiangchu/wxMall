package com.xie.service;

import com.xie.bean.Point;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-24 下午6:46
 */
public interface PointService {

    public Point getByUid(Integer uid);

    public Point getById(int id);

    public int insert(Point point);

    public int update(Point point);

    public int add(int uid, double money, int point);

    public int delete(Point point);

    public int delete(int id);
}
