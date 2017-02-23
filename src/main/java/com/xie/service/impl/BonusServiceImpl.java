package com.xie.service.impl;

import com.xie.bean.Bonus;
import com.xie.dao.BonusDao;
import com.xie.service.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/23 上午11:13.
 */
@Service
public class BonusServiceImpl implements BonusService {

    @Autowired
    BonusDao bonusDao;

    @Override
    public List<Bonus> getAllByUid(Integer uid) {
        return bonusDao.getAllByUid(uid);
    }

    @Override
    public Bonus getById(Integer id) {
        return bonusDao.getById(id);
    }

    @Override
    public int countByUid(Integer uid) {
        return bonusDao.countByUid(uid);
    }

    @Override
    public int insert(Bonus bonus) {
        return bonusDao.insert(bonus);
    }

    @Override
    public int update(Bonus bonus) {
        return bonusDao.update(bonus);
    }

    @Override
    public int delete(Integer id) {
        return bonusDao.delete(id);
    }

    @Override
    public int delete(Bonus bonus) {
        return bonusDao.delete(bonus);
    }

    @Override
    public int softDelete(Integer id) {
        return bonusDao.softDelete(id);
    }

    @Override
    public int saveOrUpdate(Bonus bonus) {
        return bonusDao.saveOrUpdate(bonus);
    }
}
