package com.xie.service.impl;

import com.xie.bean.OrderItem;
import com.xie.dao.OrderItemDao;
import com.xie.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/24 下午8:50.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public OrderItem getById(int id) {
        return orderItemDao.getById(id);
    }

    @Override
    public List<OrderItem> getByOid(int oid) {
        return orderItemDao.getByOid(oid);
    }

    @Override
    public int countByOid(int oid) {
        return orderItemDao.countByOid(oid);
    }

    @Override
    public int count() {
        return orderItemDao.count();
    }

    @Override
    public int sumAmountByOid(int oid) {
        return orderItemDao.sumAmountByOid(oid);
    }

    @Override
    public int insert(OrderItem orderItem) {
        return orderItemDao.insert(orderItem);
    }

    @Override
    public int insert(List<OrderItem> orderItems) {
        return orderItemDao.insert(orderItems);
    }

    @Override
    public int update(OrderItem orderItem) {
        return orderItemDao.update(orderItem);
    }

    @Override
    public int delete(OrderItem orderItem) {
        return orderItemDao.delete(orderItem);
    }

    @Override
    public int delete(int id) {
        return orderItemDao.delete(id);
    }

    @Override
    public int deleteByOid(int oid) {
        return orderItemDao.deleteByOid(oid);
    }
}
