package com.xie.service.impl;

import com.xie.bean.OrderLog;
import com.xie.dao.OrderLogDao;
import com.xie.service.OrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-15 下午3:24
 */
@Service
public class OrderLogServiceImpl implements OrderLogService {

    @Autowired
    private OrderLogDao orderLogDao;

    @Override
    public OrderLog getById(int id) {
        return orderLogDao.getById(id);
    }

    @Override
    public List<OrderLog> getByOid(int oid,List<Integer> types) {
        return orderLogDao.getByOid(oid,types);
    }

    @Override
    public int insert(OrderLog orderLog) {
        return orderLogDao.insert(orderLog);
    }

    @Override
    public int countByOid() {
        return orderLogDao.countByOid();
    }

    @Override
    public int update(OrderLog orderLog) {
        return orderLogDao.update(orderLog);
    }

    @Override
    public int delete(OrderLog orderLog) {
        return orderLogDao.delete(orderLog);
    }

    @Override
    public int delete(int id) {
        return orderLogDao.delete(id);
    }

    @Override
    public int deleteByOid(int oid) {
        return orderLogDao.deleteByOid(oid);
    }
}
