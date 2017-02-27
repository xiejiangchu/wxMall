package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Order;

/**
 * @Author xie
 * @Date 17/1/23 上午10:32.
 */
public interface OrderService {

    Order getById(int id);

    PageInfo<Order> getAllByUid(int uid, int pageNum, int pageSize);

    PageInfo<Order> getAll(int pageNum, int pageSize);

    PageInfo<Order> getByType(int type,int pageNum, int pageSize);

    int countByUid(int uid);

    int insert(Order order);

    int update(Order order);

    int submit(int uid, int aid, int bid, int pid, String message);

    int delete(Order order);

    int delete(int id);

    int softDelete(int id);

}
