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

     BonusExchange getById(Integer id);

     BonusExchange getByCode(String code);

     int fetchBonusByCode(int uid,String code);

     int invalid(int id);

     int insert(BonusExchange bonusExchange);

     int update(BonusExchange bonusExchange);

     int delete(Integer id);

     int delete(BonusExchange bonusExchange);
}
