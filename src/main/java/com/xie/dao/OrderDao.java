package com.xie.dao;

import com.xie.bean.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 16/11/24.
 */

@Component
public class OrderDao extends BaseDao {

    public Order getById(int id) {
        return this.sqlSession.selectOne("OrderMapper.getById", id);
    }

    public List<Order> getByStatus(Integer uid, Integer order_status, Integer pay_status, Integer ship_status, Integer package_status) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("pay_status", pay_status);
        map.put("order_status", order_status);
        map.put("ship_status", ship_status);
        map.put("package_status", package_status);
        return this.sqlSession.selectList("OrderMapper.getByStatus", map);
    }

    public int countByStatus(Integer uid, Integer order_status, Integer pay_status, Integer ship_status, Integer package_status) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("pay_status", pay_status);
        map.put("order_status", order_status);
        map.put("ship_status", ship_status);
        map.put("package_status", package_status);
        return this.sqlSession.selectOne("OrderMapper.countByStatus", map);
    }

    public List<Order> getAll() {
        return this.sqlSession.selectList("OrderMapper.getAll");
    }

    public int count(Date start, Date end) {
        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("end", end);
        return this.sqlSession.selectOne("OrderMapper.count", map);
    }

    public List<Order> getAllByUid(int uid) {
        return this.sqlSession.selectList("OrderMapper.getAllByUid", uid);
    }

    public int countByUid(int uid) {
        return this.sqlSession.selectOne("OrderMapper.countByUid", uid);
    }

    public int insert(Order item) {
        this.sqlSession.insert("OrderMapper.insert", item);
        return item.getId();
    }

    public int update(Order item) {
        return this.sqlSession.update("OrderMapper.update", item);
    }

    public int delete(Order item) {
        Assert.notNull(item);
        Assert.isTrue(item.getId() > 0);
        return this.sqlSession.delete("OrderMapper.delete", item.getId());
    }

    public int delete(int id) {
        Assert.isTrue(id > 0);
        return this.sqlSession.delete("OrderMapper.delete", id);
    }

    public int softDelete(int id) {
        Assert.isTrue(id > 0);
        return this.sqlSession.update("OrderMapper.softDelete", id);
    }

}