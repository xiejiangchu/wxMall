package com.xie.service;

import com.xie.bean.BonusExchange;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-04-13 下午3:15
 */
public interface BonusExchangeService {

    public BonusExchange getById(Integer id);

    public BonusExchange getByCode(String code);

    public int fetchBonusByCode(int uid,String code);

    public int invalid(int id);

    public int insert(BonusExchange bonusExchange);

    public int update(BonusExchange bonusExchange);

    public int delete(Integer id);

    public int delete(BonusExchange bonusExchange);
}
