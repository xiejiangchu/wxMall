package com.xie.service;

import com.xie.bean.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
public interface CartService {
    List<Cart> getByUid(int uid);
}
