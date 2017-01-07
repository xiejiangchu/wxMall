package com.xie.service.impl;

import com.xie.bean.Cart;
import com.xie.mapper.CartMapper;
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
    CartMapper cartMapper;

    @Override
    public List<Cart> getByUid(int uid) {
        return cartMapper.getByUid(uid);
    }
}
