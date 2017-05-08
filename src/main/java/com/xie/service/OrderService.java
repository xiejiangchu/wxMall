package com.xie.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xie.bean.Order;
import com.xie.pay.model.OrderReturnInfo;
import com.xie.response.OrderCheckDto;
import com.xie.response.OrderCountDto;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Date;

/**
 * @Author xie
 * @Date 17/1/23 上午10:32.
 */
public interface OrderService {

    Order getById(int id);

    Order getByNo(String no);

    PageInfo<Order> getAllByUid(int uid, int pageNum, int pageSize);

    PageInfo<Order> getAll(int type, Date created_at_start, Date created_at_end, Date time_start, Date time_end, int pageNum, int pageSize);

    PageInfo<Order> getByType(int uid, int type, int pageNum, int pageSize);

    PageInfo<Order> getAllByType(int type, int pageNum, int pageSize);

    OrderCheckDto check(int uid);

    int countByUid(int uid);

    int orderMore(int uid, int oid);

    int insert(Order order);

    int update(Order order);

    int submit(int uid, int point, int aid, int bid, int pid, Date date, Date time_start, Date time_end, String message);

    int delete(Order order);

    int delete(int id);

    int softDelete(int id);

    int count(Date start, Date end);

    int cancel(int uid, int oid);

    int packageOrder(int oid,int package_status);

    int sendOrder(int oid,int sending_status);

    int cancelOrder(int oid);

    int updatePrepayId(int oid,String prepay_id);

    OrderCountDto orderCount(int uid);

    OrderReturnInfo pay(int uid, int oid, String ip) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, IllegalAccessException, UnrecoverableKeyException;

    JSONObject sign(String repay_id);
}
