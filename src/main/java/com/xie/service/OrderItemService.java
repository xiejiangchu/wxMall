package com.xie.service;

import com.xie.bean.OrderItem;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/24 下午6:33.
 */
public interface OrderItemService {

    OrderItem getById(int id);

    List<OrderItem> getByOid(int oid);

    int countByOid(int oid);

    int count();

    int sumAmountByOid(int oid);

    int insert(OrderItem orderItem);

    int insert(List<OrderItem> orderItem);

    int update(OrderItem orderItem);

    int delete(OrderItem orderItem);

    int delete(int id);

    int deleteByOid(int oid);
}
