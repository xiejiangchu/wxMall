package com.xie.controller.api;

import com.xie.bean.Order;
import com.xie.response.BaseResponse;
import com.xie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xie on 16/11/24.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:MM:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getAll(@RequestParam("type") int type,
                        @RequestParam(value = "created_at_start", required = false) Date created_at_start,
                        @RequestParam(value = "created_at_end", required = false) Date created_at_end,
                        @RequestParam(value = "time_start", required = false) Date time_start,
                        @RequestParam(value = "time_end", required = false) Date time_end,
                        @RequestParam("pageNum") int pageNum,
                        @RequestParam("pageSize") int pageSize) {
        return BaseResponse.ok(orderService.getAll(type, created_at_start, created_at_end, time_start, time_end, pageNum, pageSize));
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
                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                      HttpSession session) {
        return BaseResponse.ok(orderService.getByType(getUid(session), type, pageNum, pageSize));
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
    public BaseResponse check(HttpSession session) {
        return BaseResponse.ok(orderService.check(getUid(session)));
    }

    @RequestMapping(value = "/orderMore", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse orderMore(@RequestParam("oid") int oid, HttpSession session) {
        int result = orderService.orderMore(getUid(session), oid);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse submit(@RequestParam("aid") int aid,
                               @RequestParam("bid") int bid,
                               @RequestParam("pid") int pid,
                               @RequestParam("date") Date date,
                               @RequestParam("time_start") Date time_start,
                               @RequestParam("time_end") Date time_end,
                               @RequestParam("message") String message,
                               HttpSession session) {
        int result = orderService.submit(getUid(session), aid, bid, pid, date, time_start, time_end, message);
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
    public BaseResponse ordercount(HttpSession session) {
        return BaseResponse.ok(orderService.orderCount(getUid(session)));
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse cancel(@RequestParam("oid") int oid, HttpSession session) {
        return BaseResponse.ok(orderService.cancel(getUid(session), oid));
    }
}
