package com.xie.service.impl;

import com.xie.bean.Bonus;
import com.xie.bean.BonusType;
import com.xie.dao.BonusDao;
import com.xie.service.BonusService;
import com.xie.service.BonusTypeService;
import org.joda.time.DateTimeComparator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * @Author xie
 * @Date 17/2/23 上午11:13.
 */
@Service
public class BonusServiceImpl implements BonusService {

    @Autowired
    BonusDao bonusDao;

    @Autowired
    BonusTypeService bonusTypeService;

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
    public int insert(Integer uid, Integer tid, Integer is_enable, Date begin, Date end) {
        Assert.notNull(uid);
        Assert.notNull(tid);
        BonusType bonusType = bonusTypeService.getById(tid);
        Assert.notNull(bonusType);


        Bonus insert = new Bonus();
        insert.setUid(uid);
        insert.setTid(tid);
        insert.setIs_enable(is_enable);
        BeanUtils.copyProperties(bonusType, insert, "id", "uid", "tid", "begin", "end", "created_at", "updated_at");
        if (DateTimeComparator.getInstance().compare(begin, bonusType.getBegin()) > 0) {
            insert.setBegin(begin);
        } else {
            insert.setBegin(bonusType.getBegin());
        }
        if (DateTimeComparator.getInstance().compare(end, bonusType.getEnd()) < 0) {
            insert.setEnd(end);
        } else {
            insert.setEnd(bonusType.getEnd());
        }

        return bonusDao.insert(insert);
    }

    @Override
    public int insert(Integer uid, Integer tid) {
        Assert.notNull(uid);
        Assert.notNull(tid);
        BonusType bonusType = bonusTypeService.getById(tid);
        Assert.notNull(bonusType);

        Bonus insert = new Bonus();
        insert.setUid(uid);
        insert.setTid(tid);
        insert.setIs_enable(1);
        BeanUtils.copyProperties(bonusType, insert, "id", "uid", "tid", "created_at", "updated_at");

        return bonusDao.insert(insert);
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
