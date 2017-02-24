package com.xie.dao;

import com.xie.bean.OrderItem;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/24 下午8:52.
 */
@Component
public class OrderItemDao extends BaseDao {

    public OrderItem getById(int id) {
        return sqlSession.selectOne("OrderItemMapper.getById", id);
    }

    public List<OrderItem> getByOid(int oid) {
        return sqlSession.selectList("OrderItemMapper.getByOid", oid);
    }

    public int countByOid(int oid) {
        return sqlSession.selectOne("OrderItemMapper.countByOid", oid);
    }

    public int count() {
        return sqlSession.selectOne("OrderItemMapper.count");
    }

    public int sumAmountByOid(int oid) {
        return sqlSession.selectOne("OrderItemMapper.sumAmountByOid", oid);
    }

    public int insert(OrderItem orderItem) {
        return sqlSession.insert("OrderItemMapper.insert", orderItem);
    }

    public int update(OrderItem orderItem) {
        return sqlSession.update("OrderItemMapper.update", orderItem);
    }

    public int delete(OrderItem orderItem) {
        Assert.notNull(orderItem);
        return sqlSession.delete("OrderItemMapper.delete", orderItem.getId());
    }

    public int delete(int id) {
        return sqlSession.delete("OrderItemMapper.delete", id);
    }

    public int deleteByOid(int oid) {
        return sqlSession.delete("OrderItemMapper.deleteByOid", oid);
    }
}
