package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Bonus;

import java.util.Date;
import java.util.List;

/**
 * @Author xie
 * @Date 17/2/23 上午11:12.
 */
public interface BonusService {

    List<Bonus> getAllByUid(int uid);

    PageInfo<Bonus> getListByType(int uid, int type, int pageNum, int pageSize);

    Bonus getById(int id);

    int countByUid(int uid);

    int insert(Bonus bonus);

    int insert(int uid, int tid, Integer is_enable, Date begin, Date end);

    int insert(int uid, int tid);

    int update(Bonus bonus);

    int delete(int id);

    int delete(Bonus bonus);

    int softDelete(int id);

    int saveOrUpdate(Bonus bonus);
}
