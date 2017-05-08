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

    public Order getByNo(String no) {
        Assert.isTrue(no != null, "订单编号不能为空");
        return this.sqlSession.selectOne("OrderMapper.getByNo", no);
    }

    public List<Order> getByStatus(Integer uid, List<Integer> order_status, List<Integer> pay_status, List<Integer> ship_status, List<Integer> package_status) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("pay_status", pay_status);
        map.put("order_status", order_status);
        map.put("ship_status", ship_status);
        map.put("package_status", package_status);
        return this.sqlSession.selectList("OrderMapper.getByStatus", map);
    }

    public List<Order> getAllByStatus(Integer order_status, Integer pay_status, Integer ship_status, Integer package_status) {
        Map<String, Object> map = new HashMap<>();
        map.put("pay_status", pay_status);
        map.put("order_status", order_status);
        map.put("ship_status", ship_status);
        map.put("package_status", package_status);
        return this.sqlSession.selectList("OrderMapper.getAllByStatus", map);
    }

    public List<Order> getAll(Integer order_status, Integer pay_status, Integer ship_status, Integer package_status, Date created_at_start, Date created_at_end, Date time_start, Date time_end) {
        Map<String, Object> map = new HashMap<>();
        map.put("pay_status", pay_status);
        map.put("order_status", order_status);
        map.put("ship_status", ship_status);
        map.put("package_status", package_status);
        map.put("created_at_start", created_at_start);
        map.put("created_at_end", created_at_end);
        map.put("time_start", time_start);
        map.put("time_end", time_end);
        return this.sqlSession.selectList("OrderMapper.getAllByStatus", map);
    }


    public int countByStatus(Integer uid, List<Integer> order_status, List<Integer> pay_status, List<Integer> ship_status, List<Integer> package_status) {
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

    public int cancel(Order item) {
        return this.sqlSession.update("OrderMapper.cancel", item);
    }

    public int packageOrder(Order item) {
        return this.sqlSession.update("OrderMapper.packageOrder", item);
    }

    public int sendOrder(Order item) {
        return this.sqlSession.update("OrderMapper.sendOrder", item);
    }

    public int delete(Order item) {
        Assert.notNull(item);
        Assert.isTrue(item.getId() > 0);
        return this.sqlSession.delete("OrderMapper.delete", item.getId());
    }

    public int check(int uid, int oid) {
        Assert.isTrue(uid > 0);
        Assert.isTrue(oid > 0);
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("oid", oid);
        return this.sqlSession.selectOne("OrderMapper.check", map);
    }

    public int delete(int id) {
        Assert.isTrue(id > 0);
        return this.sqlSession.delete("OrderMapper.delete", id);
    }

    public int softDelete(int id) {
        Assert.isTrue(id > 0);
        return this.sqlSession.update("OrderMapper.softDelete", id);
    }

    public int updatePrepayId(int id, String prepay_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("prepay_id", prepay_id);
        return this.sqlSession.update("OrderMapper.updatePrepayId", map);
    }

}