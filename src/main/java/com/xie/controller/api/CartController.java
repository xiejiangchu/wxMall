package com.xie.controller.api;

import com.xie.response.BaseResponse;
import com.xie.service.CartService;
import com.xie.service.ItemService;
import com.xie.service.ItemSpecService;
import com.xie.utils.MallConstants;
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

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemSpecService itemSpecService;

    @RequestMapping(value = "/clear", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse clear(@RequestParam(value = "sessionId", required = true) String sessionId) {
        cartService.clear(getUid(sessionId));
        return BaseResponse.ok(cartService.getByUidWithItem(getUid(sessionId)));
    }

    @RequestMapping(value = "item/", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getByUidWithItem(@RequestParam(value = "sessionId", required = true) String sessionId) {
        return BaseResponse.ok(cartService.getByUidWithItem(getUid(sessionId)));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse update(@RequestHeader(value = "sessionId") String sessionId, @RequestParam("gid") int gid, @RequestParam("spec") int spec, @RequestParam("amount") int amount) {
        int uid = getUid(sessionId);
        if (itemService.getById(gid).getIs_online() == MallConstants.NO || itemSpecService.getById(spec).getIs_online() == MallConstants.NO) {
            return BaseResponse.fail("商品已经下架");
        }
        int result = -1;
        if (amount > 0) {
            result = cartService.saveOrUpdate(uid, gid, spec, amount);
        } else {
            result = cartService.deleteByGidAndSpec(gid, spec);
        }
        if (result > 0) {
            return BaseResponse.ok(cartService.getByUidWithItem(uid));
        } else {
            return BaseResponse.ok(cartService.getByUidWithItem(uid));
        }

    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse insert(@RequestParam("uid") int uid, @RequestParam("gid") int gid, @RequestParam("spec") int spec, @RequestParam("amount") int amount) {
        cartService.saveOrUpdate(uid, gid, spec, amount);
        return BaseResponse.ok(cartService.getByUidWithItem(uid));
    }

}
