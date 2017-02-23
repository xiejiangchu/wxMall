package com.xie.service;

import com.xie.bean.BonusType;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/23 下午12:20.
 */
public interface BonusTypeService {

    List<BonusType> getAll();

    List<BonusType> getAllEnabled();

    List<BonusType> getAllByGid(Integer gid);

    List<BonusType> getAllByCid(Integer cid1, Integer cid2);

    BonusType getById(Integer id);

    int countByGid(Integer gid);

    int insert(BonusType bonusType);

    int update(BonusType bonusType);

    int delete(Integer id);

    int delete(BonusType bonusType);

    int softDelete(Integer id);

    int saveOrUpdate(BonusType bonus);

}
