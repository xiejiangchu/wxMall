package com.xie.service.impl;

import com.xie.bean.BonusType;
import com.xie.dao.BonusTypeDao;
import com.xie.service.BonusTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/23 下午12:21.
 */
@Service
public class BonusTypeServiceImpl implements BonusTypeService {

    @Autowired
    private BonusTypeDao bonusTypeDao;

    @Override
    public List<BonusType> getAll() {
        return bonusTypeDao.getAll();
    }

    @Override
    public List<BonusType> getAllEnabled() {
        return bonusTypeDao.getAllEnabled();
    }

    @Override
    public List<BonusType> getAllByGid(Integer gid) {
        return bonusTypeDao.getAllByGid(gid);
    }

    @Override
    public List<BonusType> getAllByCid(Integer cid1, Integer cid2) {
        return bonusTypeDao.getAllByCid(cid1, cid2);
    }

    @Override
    public BonusType getById(Integer id) {
        return bonusTypeDao.getById(id);
    }

    @Override
    public int countByGid(Integer gid) {
        return bonusTypeDao.countByGid(gid);
    }

    @Override
    public int insert(BonusType bonusType) {
        return bonusTypeDao.insert(bonusType);
    }

    @Override
    public int update(BonusType bonusType) {
        return bonusTypeDao.update(bonusType);
    }

    @Override
    public int delete(Integer id) {
        return bonusTypeDao.delete(id);
    }

    @Override
    public int delete(BonusType bonusType) {
        return bonusTypeDao.delete(bonusType);
    }

    @Override
    public int softDelete(Integer id) {
        return bonusTypeDao.softDelete(id);
    }

    @Override
    public int saveOrUpdate(BonusType bonus) {
        return bonusTypeDao.saveOrUpdate(bonus);
    }
}
