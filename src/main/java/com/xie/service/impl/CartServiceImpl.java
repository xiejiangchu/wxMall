package com.xie.service.impl;

import com.xie.bean.Cart;
import com.xie.dao.CartDao;
import com.xie.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartDao;

    @Override
    public List<Cart> getByUid(int uid) {
        return cartDao.getByUid(uid);
    }

    @Override
    public int clear(int uid) {
        return cartDao.clear(uid);
    }

    @Override
    public int insert(Cart cart) {
        return cartDao.insert(cart);
    }

    @Override
    public int update(Cart cart) {
        return cartDao.update(cart);
    }

    @Override
    public int delete(Cart cart) {
        return cartDao.delete(cart);
    }

    @Override
    public int delete(int id) {
        return cartDao.delete(id);
    }

    @Override
    public int saveOrUpdate(int uid, int gid, int spec, int amount) {
        return cartDao.saveOrUpdate(uid, gid, spec, amount);
    }

    @Override
    public int clearByUid(int uid) {
        return cartDao.clearByUid(uid);
    }

    @Override
    public int deleteByGidAndSpec(int gid, int spec) {
        return cartDao.deleteByGidAndSpec(gid,spec);
    }

    @Override
    public List<Cart> getByUidWithItem(int uid) {
        return cartDao.getByUidWithItem(uid);
    }
}
