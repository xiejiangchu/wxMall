package com.xie.service;

import com.xie.bean.Bonus;

import java.util.Date;
import java.util.List;

/**
 * @Author xie
 * @Date 17/2/23 上午11:12.
 */
public interface BonusService {

    public List<Bonus> getAllByUid(Integer uid);

    public Bonus getById(Integer id);

    public int countByUid(Integer uid);

    public int insert(Bonus bonus);

    public int insert(Integer uid, Integer tid, Integer is_enable, Date begin, Date end);

    public int insert(Integer uid, Integer tid);

    public int update(Bonus bonus);

    public int delete(Integer id);

    public int delete(Bonus bonus);

    public int softDelete(Integer id);

    public int saveOrUpdate(Bonus bonus);
}
