package com.xie.controller.api;

import com.xie.response.BaseResponse;
import com.xie.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xie on 17/1/7.
 */
@Controller
@RequestMapping(value = "/cart")
public class CartController extends BaseController{

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('manager_orders', 'manager_orders')")
    public BaseResponse cart(@PathVariable("uid") int uid) {

        return BaseResponse.ok(cartService.getByUid(uid));
    }

    @RequestMapping(value = "item/{uid}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('manager_orders', 'manager_orders')")
    public BaseResponse getByUidWithItem(@PathVariable("uid") int uid) {
        return BaseResponse.ok(cartService.getByUidWithItem(uid));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('manager_orders', 'manager_orders')")
    public BaseResponse update(@RequestParam("uid") int uid, @RequestParam("gid") int gid, @RequestParam("spec") int spec, @RequestParam("amount") int amount) {

        return BaseResponse.ok(cartService.saveOrUpdate(uid, gid, spec, amount));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('manager_orders', 'manager_orders')")
    public BaseResponse insert(@RequestParam("uid") int uid, @RequestParam("gid") int gid, @RequestParam("spec") int spec, @RequestParam("amount") int amount) {
        return BaseResponse.ok(cartService.saveOrUpdate(uid, gid, spec, amount));
    }

}
