package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Bonus;
import com.xie.bean.BonusType;
import com.xie.bean.Cart;

import java.util.Date;
import java.util.List;

/**
 * @Author xie
 * @Date 17/2/23 上午11:12.
 */
public interface BonusService {

    List<Bonus> getAllByUid(int uid);

    PageInfo<Bonus> getListByType(int uid, int type, int pageNum, int pageSize);

    PageInfo<Bonus> getAll(int pageNum, int pageSize);

    Bonus getById(int id);

    Bonus getEnabledById(int id);

    int countByUid(int uid);

    int countEnabledByUid(int uid);

    int countEnabledByCart(int uid, List<Cart> carts);

    List<Bonus> getEnabledByCart(int uid, List<Cart> carts);

    int insert(Bonus bonus);

    int insert(int uid, int tid, Integer is_enable, Date begin, Date end);

    int insert(int uid, BonusType bonusType);

    int insert(int uid, int tid);

    int update(Bonus bonus);

    int delete(int id);

    int delete(Bonus bonus);

    int softDelete(int id);

    int saveOrUpdate(Bonus bonus);

    Bonus fetchBonusByCode(int uid, String code);

    int offline(int id,int online);
}
