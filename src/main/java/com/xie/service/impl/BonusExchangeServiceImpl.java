package com.xie.service.impl;

import com.xie.bean.BonusExchange;
import com.xie.bean.BonusType;
import com.xie.dao.BonusExchangeDao;
import com.xie.service.BonusExchangeService;
import com.xie.service.BonusService;
import com.xie.service.BonusTypeService;
import com.xie.utils.MallConstants;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class BonusExchangeServiceImpl implements BonusExchangeService {

    @Autowired
    BonusExchangeDao bonusExchangeDao;

    @Autowired
    BonusTypeService bonusTypeService;

    @Autowired
    BonusService bonusService;

    @Override
    public BonusExchange getById(Integer id) {
        return bonusExchangeDao.getById(id);
    }

    @Override
    public BonusExchange getByCode(String code) {
        return bonusExchangeDao.getByCode(code);
    }

    @Transactional
    @Override
    public int fetchBonusByCode(int uid, String code) {
        BonusExchange bonusExchange = bonusExchangeDao.getByCode(code);
        if (bonusExchange.getDeleted_at() != null && DateTime.now().toDate().compareTo(bonusExchange.getDeleted_at()) > 0 && bonusExchange.getNumber() > 0) {
            return -1;
        } else {
            BonusType bonusType = bonusTypeService.getById(bonusExchange.getTid());
            if (bonusType != null && bonusType.getIs_enable() == MallConstants.YES) {
                int left = bonusExchange.getNumber() - 1;
                bonusExchange.setNumber(left);
                //更新
                update(bonusExchange);
                //插入用户红包
                return bonusService.insert(uid, bonusType);
            }

        }
        return -1;
    }

    @Override
    public int invalid(int id) {
        return bonusExchangeDao.invalid(id);
    }

    @Override
    public int insert(BonusExchange bonusExchange) {
        return bonusExchangeDao.insert(bonusExchange);
    }

    @Override
    public int update(BonusExchange bonusExchange) {
        return bonusExchangeDao.update(bonusExchange);
    }

    @Override
    public int delete(Integer id) {
        return bonusExchangeDao.delete(id);
    }

    @Override
    public int delete(BonusExchange bonusExchange) {
        return bonusExchangeDao.delete(bonusExchange);
    }
}
