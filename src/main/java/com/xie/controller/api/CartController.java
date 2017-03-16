package com.xie.controller.api;

import com.xie.response.BaseResponse;
import com.xie.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xie on 17/1/7.
 */
@Controller
@RequestMapping(value = "/cart")
public class CartController extends BaseController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse cart(@PathVariable("uid") int uid) {

        return BaseResponse.ok(cartService.getByUid(uid));
    }

    @RequestMapping(value = "/clear", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse clear(@RequestParam("uid") int uid) {

        return BaseResponse.ok(cartService.clear(uid));
    }

    @RequestMapping(value = "item/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getByUidWithItem(@PathVariable("uid") int uid) {
        return BaseResponse.ok(cartService.getByUidWithItem(uid));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse update(@RequestParam("uid") int uid, @RequestParam("gid") int gid, @RequestParam("spec") int spec, @RequestParam("amount") int amount) {
        cartService.saveOrUpdate(uid, gid, spec, amount);
        return BaseResponse.ok(cartService.getByUidWithItem(uid));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse insert(@RequestParam("uid") int uid, @RequestParam("gid") int gid, @RequestParam("spec") int spec, @RequestParam("amount") int amount) {
        cartService.saveOrUpdate(uid, gid, spec, amount);
        return BaseResponse.ok(cartService.getByUidWithItem(uid));
    }

}
