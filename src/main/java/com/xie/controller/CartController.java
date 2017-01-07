package com.xie.controller;

import com.xie.bean.Cart;
import com.xie.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "cart/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public List<Cart> cart(@PathVariable("uid") int uid) {
        return cartService.getByUid(uid);
    }
}
