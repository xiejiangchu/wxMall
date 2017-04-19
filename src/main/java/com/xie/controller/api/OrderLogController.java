package com.xie.controller.api;

import com.xie.enums.ActionType;
import com.xie.response.BaseResponse;
import com.xie.service.OrderLogService;
import com.xie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * Created by xie on 16/11/24.
 */
@Controller
@RequestMapping(value = "/orderLog")
public class OrderLogController extends BaseController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderLogService orderLogService;


    @RequestMapping(value = "/getByOid", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getAll(@RequestParam("oid") int oid) {
        return BaseResponse.ok(orderLogService.getByOid(oid, Arrays.asList(ActionType.订单操作.value())));
    }

}
