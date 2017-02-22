package com.xie.service;

import com.xie.bean.Cart;

import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
public interface CartService {
    List<Cart> getByUid(int uid);

    List<Cart> getByUidWithItem(int uid);

    int insert(Cart cart);

    int saveOrUpdate(int uid, int gid, int spec, int amount);

    int update(Cart cart);

    int delete(Cart cart);

    int delete(int id);
}
