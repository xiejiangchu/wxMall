package com.xie.controller.api;

import com.xie.response.BaseResponse;
import com.xie.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xie on 17/1/7.
 */
@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('manager_orders', 'manager_orders')")
    public BaseResponse cart(@PathVariable("uid") int uid) {

        return BaseResponse.ok(cartService.getByUid(uid));
    }
}
