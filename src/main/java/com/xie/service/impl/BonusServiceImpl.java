package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.Bonus;
import com.xie.bean.BonusType;
import com.xie.bean.Cart;
import com.xie.bean.Item;
import com.xie.dao.BonusDao;
import com.xie.enums.BonusQueryType;
import com.xie.service.BonusService;
import com.xie.service.BonusTypeService;
import com.xie.utils.MallConstants;
import org.joda.time.DateTimeComparator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Bonus getEnabledById(int id) {
        return bonusDao.getEnabledById(id);
    }

    @Override
    public int countByUid(int uid) {
        return bonusDao.countByUid(uid);
    }

    @Override
    public int countEnabledByUid(int uid) {
        return bonusDao.countEnabledByUid(uid);
    }

    @Override
    public int countEnabledByCart(int uid, List<Cart> carts) {
        return getEnabledByCart(uid, carts).size();
    }

    @Override
    public List<Bonus> getEnabledByCart(int uid, List<Cart> carts) {
        List<Bonus> bonusList = bonusDao.getListValidate(uid);
        double total = 0;
        Map<Integer, Double> cid1Total = new HashMap<>();
        Map<Integer, Double> cid2Total = new HashMap<>();
        for (int i = 0; i < carts.size(); i++) {
            Cart cart = carts.get(i);
            double subtotal = cart.getItemSpec().getShop_price() * cart.getAmount();
            total += subtotal;
            Item item = cart.getItem();

            if (cid2Total.get(item.getCid2()) == null) {
                cid2Total.put(item.getCid2(), subtotal);
            } else {
                cid2Total.put(item.getCid2(), cid2Total.get(item.getCid2()) + subtotal);
            }

            if (cid1Total.get(item.getCid1()) == null) {
                cid1Total.put(item.getCid1(), subtotal);
            } else {
                cid1Total.put(item.getCid1(), cid1Total.get(item.getCid1()) + subtotal);
            }
        }
        final double finalTotal = total;
        List<Bonus> output = bonusList.stream().filter(bonus -> {

            for (int i = 0; i < carts.size(); i++) {
                Cart cart = carts.get(i);
                double subtotal = cart.getAmount() * cart.getItemSpec().getShop_price();
                if (bonus.getGid() > 0) {
                    if (bonus.getGid() == cart.getGid() && subtotal >= bonus.getMin_amount()) {
                        return true;
                    }
                    return false;
                } else if (bonus.getCid2() > 0) {
                    if (bonus.getCid2() == cart.getItem().getCid2() && cid2Total.get(bonus.getCid2()) >= bonus.getMin_amount()) {
                        return true;
                    }
                    return false;
                } else if (bonus.getCid1() > 0) {
                    if (bonus.getCid1() == cart.getItem().getCid1() && cid1Total.get(bonus.getCid1()) >= bonus.getMin_amount()) {
                        return true;
                    }
                    return false;
                } else {
                    return finalTotal > bonus.getMin_amount();
                }
            }
            return false;
        }).collect(Collectors.toList());
        return output;
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
    public int insert(int uid, BonusType bonusType) {
        Bonus insert = new Bonus();
        insert.setUid(uid);
        insert.setTid(bonusType.getId());
        insert.setIs_enable(MallConstants.YES);

        BeanUtils.copyProperties(bonusType, insert, "id", "uid", "tid", "created_at", "updated_at");

        return bonusDao.insert(insert);
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

    @Override
    public int fetchBonusByCode(int uid, String code) {
        return 0;
    }
}
