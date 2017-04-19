package com.xie.controller.api;

import com.xie.bean.Bonus;
import com.xie.bean.Cart;
import com.xie.enums.BonusQueryType;
import com.xie.response.BaseResponse;
import com.xie.service.BonusExchangeService;
import com.xie.service.BonusService;
import com.xie.service.BonusTypeService;
import com.xie.service.CartService;
import com.xie.utils.MallConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
@Controller
@RequestMapping(value = "/bonus")
public class BonusController extends BaseController {

    @Autowired
    BonusService bonusService;

    @Autowired
    BonusTypeService bonusTypeService;

    @Autowired
    CartService cartService;

    @Autowired
    private BonusExchangeService bonusExchangeService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathVariable("id") int id) {
        return BaseResponse.ok(bonusService.getById(id));
    }

    /**
     * 后台管理
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse getAll(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(bonusService.getAll(pageNum, pageSize));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse list(
            @RequestParam(value = "type") int type,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(bonusService.getListByType(getUid(), type, pageNum, pageSize));
    }

    @RequestMapping(value = "/getEnabledByCart", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getEnabledByCart() {
        int uid = getUid();
        List<Cart> cartList = cartService.getByUidWithItem(uid);
        List<Cart> carts = new ArrayList<>();
        for (int i = 0; i < cartList.size(); i++) {
            Cart cart = cartList.get(i);
            if (cart.getItem().getIs_online() == MallConstants.YES && cart.getItemSpec().getIs_online() == MallConstants.YES) {
                carts.add(cart);
            }
        }
        return BaseResponse.ok(bonusService.getEnabledByCart(uid, carts));
    }

    @RequestMapping(value = "/fetchBonusByCode", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse fetchBonusByCode(
            @RequestParam(value = "code") String code) {
        int uid = getUid();
        int result = bonusExchangeService.fetchBonusByCode(uid, code);
        if (result > 0) {
            return BaseResponse.ok(bonusService.getListByType(getUid(), BonusQueryType.未使用.value(), 1, 10));
        } else {
            return BaseResponse.fail("兑换失败");
        }

    }


    @RequestMapping(value = "/getAllEnabled", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    BaseResponse getAllEnabled() {
        return BaseResponse.ok(bonusTypeService.getAllEnabled());
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse post(@RequestBody Bonus bonus) {
        int result = bonusService.insert(bonus);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/give", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse post(@RequestParam(value = "uid") int uid,
                             @RequestParam(value = "tid") int tid,
                             @RequestParam(value = "is_enable", defaultValue = "0") int is_enable,
                             @RequestParam(value = "begin", defaultValue = "2017-01-01") Date begin,
                             @RequestParam(value = "end", defaultValue = "2017-01-01") Date end) {
        int result = bonusService.insert(uid, tid, is_enable, begin, end);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/offline", method = RequestMethod.PUT)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse offline2(@RequestParam(value = "id") int id,
                                 @RequestParam(value = "online") int online) {
        int result = bonusService.offline(id, online);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse put(@PathVariable("id") int id, @ModelAttribute Bonus bonus) {
        int result = bonusService.update(bonus);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse delete(@PathVariable("id") int id) {
        int result = bonusService.softDelete(id);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }

    }
}
