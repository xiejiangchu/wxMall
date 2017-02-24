package com.xie.dao;

import com.xie.bean.Payment;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @Author xie
 * @Date 17/2/24 下午9:54.
 */
@Component
public class PaymentDao extends BaseDao {

    public Payment getById(int id) {
        return sqlSession.selectOne("PaymentMapper.getById", id);
    }

    public int insert(Payment payment) {
        return sqlSession.insert("PaymentMapper.insert", payment);
    }

    public int update(Payment payment) {
        return sqlSession.update("PaymentMapper.update", payment);
    }

    public int delete(Payment payment) {
        Assert.notNull(payment);
        return sqlSession.delete("PaymentMapper.delete", payment.getId());
    }

    public int delete(int id) {
        return sqlSession.delete("PaymentMapper.delete", id);
    }

}
