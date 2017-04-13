package com.xie.dao;

import com.xie.bean.BonusExchange;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-04-12 下午12:36
 */
@Component
public class BonusExchangeDao extends BaseDao {

    public BonusExchange getById(Integer id) {
        return this.sqlSession.selectOne("BonusExchangeMapper.getById", id);
    }

    public BonusExchange getByCode(String code) {
        return this.sqlSession.selectOne("BonusExchangeMapper.getByCode", code);
    }

    public int invalid(int id){
        return this.sqlSession.update("BonusExchangeMapper.invalid", id);
    }
    public int insert(BonusExchange bonusExchange) {
        return this.sqlSession.insert("BonusExchangeMapper.insert", bonusExchange);
    }

    public int update(BonusExchange bonusExchange) {
        return this.sqlSession.update("BonusExchangeMapper.update", bonusExchange);
    }

    public int delete(Integer id) {
        return this.sqlSession.delete("BonusExchangeMapper.delete", id);
    }

    public int delete(BonusExchange bonusExchange) {
        Assert.notNull(bonusExchange);
        Assert.isTrue(bonusExchange.getId() > 0);
        return this.sqlSession.delete("BonusExchangeMapper.delete", bonusExchange.getId());
    }
}
