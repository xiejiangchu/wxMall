package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.Bonus;
import com.xie.bean.BonusType;
import com.xie.dao.BonusDao;
import com.xie.enums.BonusQueryType;
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
    public List<Bonus> getAllByUid(int uid) {
        return bonusDao.getAllByUid(uid);
    }

    @Override
    public Bonus getById(int id) {
        return bonusDao.getById(id);
    }

    @Override
    public int countByUid(int uid) {
        return bonusDao.countByUid(uid);
    }

    @Override
    public int insert(Bonus bonus) {
        return bonusDao.insert(bonus);
    }

    @Override
    public PageInfo<Bonus> getListByType(int uid, int type, int pageNum, int pageSize) {
        if (BonusQueryType.未使用.value().equals(type)) {
            PageInfo<Bonus> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> bonusDao.getListValidate(uid));
            return page;
        } else if (BonusQueryType.已过期.value().equals(type)) {
            PageInfo<Bonus> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> bonusDao.getListInvalidate(uid));
            return page;
        }
        return null;
    }

    @Override
    public int insert(int uid, int tid, Integer is_enable, Date begin, Date end) {
        Assert.notNull(uid);
        Assert.notNull(tid);
        BonusType bonusType = bonusTypeService.getById(tid);
        Assert.notNull(bonusType);


        Bonus insert = new Bonus();
        insert.setUid(uid);
        insert.setTid(tid);
        insert.setIs_enable(is_enable);
        BeanUtils.copyProperties(bonusType, insert, "id", "uid", "tid", "start_at", "end_at", "created_at", "updated_at");
        if (DateTimeComparator.getInstance().compare(begin, bonusType.getStart_at()) > 0) {
            insert.setStart_at(begin);
        } else {
            insert.setStart_at(bonusType.getStart_at());
        }
        if (DateTimeComparator.getInstance().compare(end, bonusType.getEnd_at()) < 0) {
            insert.setEnd_at(end);
        } else {
            insert.setEnd_at(bonusType.getEnd_at());
        }

        return bonusDao.insert(insert);
    }

    @Override
    public int insert(int uid, int tid) {
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
    public int delete(int id) {
        return bonusDao.delete(id);
    }

    @Override
    public int delete(Bonus bonus) {
        return bonusDao.delete(bonus);
    }

    @Override
    public int softDelete(int id) {
        return bonusDao.softDelete(id);
    }

    @Override
    public int saveOrUpdate(Bonus bonus) {
        return bonusDao.saveOrUpdate(bonus);
    }
}
