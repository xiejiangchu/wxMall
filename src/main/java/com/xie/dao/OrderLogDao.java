package com.xie.dao;

import com.xie.bean.OrderLog;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xie
 * @Date 17/2/22 下午2:39.
 */
@Component
public class OrderLogDao extends BaseDao {

    public OrderLog getById(int id) {
        return this.sqlSession.selectOne("OrderLogMapper.getById", id);
    }

    public List<OrderLog> getByOid(int oid,List<Integer> types) {
        Map<String, Object> map = new HashMap<>();
        map.put("oid", oid);
        map.put("types", types);
        return this.sqlSession.selectList("OrderLogMapper.getByOid", map);
    }

    public int insert(OrderLog orderLog) {
        this.sqlSession.insert("OrderLogMapper.insert", orderLog);
        return orderLog.getId();
    }

    public int countByOid() {
        return this.sqlSession.selectOne("OrderLogMapper.countByOid");
    }

    public int update(OrderLog orderLog) {
        return this.sqlSession.update("OrderLogMapper.update", orderLog);
    }

    public int delete(OrderLog orderLog) {
        Assert.notNull(orderLog);
        Assert.isTrue(orderLog.getId() > 0);
        return this.sqlSession.delete("OrderLogMapper.delete", orderLog.getId());
    }

    public int delete(int id) {
        return this.sqlSession.delete("OrderLogMapper.delete", id);
    }

    public int deleteByOid(int oid) {
        return this.sqlSession.delete("OrderLogMapper.deleteByOid", oid);
    }

}
