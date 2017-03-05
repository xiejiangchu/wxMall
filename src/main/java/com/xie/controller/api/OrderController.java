package com.xie.controller.api;

import com.xie.bean.Order;
import com.xie.response.BaseResponse;
import com.xie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by xie on 16/11/24.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getAll(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return BaseResponse.ok(orderService.getAll(pageNum, pageSize));
    }

    @RequestMapping(value = "/getByUid", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getByUid(@RequestParam("uid") Integer uid,
                          @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(orderService.getAllByUid(uid, pageNum, pageSize));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathVariable("id") int id) {
        return BaseResponse.ok(orderService.getById(id));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse list(@RequestParam(value = "type") int type,
                      @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(orderService.getByType(type, pageNum, pageSize));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse put(@PathVariable int id, @ModelAttribute Order item) {
        int result = orderService.update(item);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse check(@RequestParam("uid") int uid,
                              @RequestParam("aid") int aid,
                              @RequestParam("bid") int bid,
                              @RequestParam("pid") int pid,
                              @RequestParam("message") String message) {
        int result = orderService.submit(uid, aid, bid, pid, message);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse submit(@RequestParam("uid") int uid,
                               @RequestParam("aid") int aid,
                               @RequestParam("bid") int bid,
                               @RequestParam("pid") int pid,
                               @RequestParam("message") String message) {
        int result = orderService.submit(uid, aid, bid, pid, message);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse delete(@PathVariable int id) {
        int result = orderService.softDelete(id);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }

    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse count(HttpSession session) {
        return BaseResponse.ok(orderService.countByUid(getUid(session)));
    }


    @RequestMapping(value = "/orderCount", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse ordercount(HttpSession session)  {
        return BaseResponse.ok(orderService.orderCount(getUid(session)));
    }
}
