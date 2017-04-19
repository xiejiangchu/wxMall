package com.xie.service;

import com.xie.bean.OrderLog;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-15 下午3:23
 */
public interface OrderLogService {

    OrderLog getById(int id);

    List<OrderLog> getByOid(int oid,List<Integer> types);

    int insert(OrderLog orderLog);

    int countByOid();

    int update(OrderLog orderLog);

    int delete(OrderLog orderLog);

    int delete(int id);

    int deleteByOid(int oid);
}
