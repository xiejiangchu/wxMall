package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.BonusType;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/23 下午12:20.
 */
public interface BonusTypeService {

    PageInfo<BonusType> getAll(int pageNum, int pageSize);

    List<BonusType> getAllEnabled();

    List<BonusType> getAllByGid(Integer gid);

    List<BonusType> getAllByCid(Integer cid1, Integer cid2);

    BonusType getById(Integer id);

    int countByGid(Integer gid);

    int insert(BonusType bonusType);

    int update(BonusType bonusType);

    int delete(Integer id);

    int delete(BonusType bonusType);

    int saveOrUpdate(BonusType bonus);

    int offline(int id,int online);

}
