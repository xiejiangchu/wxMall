package com.xie.controller.api;

import com.xie.bean.Order;
import com.xie.pay.model.OrderReturnInfo;
import com.xie.response.BaseResponse;
import com.xie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xie on 16/11/24.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:MM:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

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
    BaseResponse list(@RequestHeader(value = "SESSIONID") String sessionId,
                      @RequestParam(value = "type") int type,
                      @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(orderService.getByType(getUid(sessionId), type, pageNum, pageSize));
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
    public BaseResponse check(@RequestHeader(value = "SESSIONID") String sessionId) {
        return BaseResponse.ok(orderService.check(getUid(sessionId)));
    }

    @RequestMapping(value = "/orderMore", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse orderMore(@RequestParam(value = "sessionId") String sessionId,
                                  @RequestParam("oid") int oid) {
        int result = orderService.orderMore(getUid(sessionId), oid);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse submit(@RequestParam(value = "sessionId") String sessionId,
                               @RequestParam("aid") int aid,
                               @RequestParam("bid") int bid,
                               @RequestParam("pid") int pid,
                               @RequestParam("date") Date date,
                               @RequestParam("time_start") Date time_start,
                               @RequestParam("time_end") Date time_end,
                               @RequestParam("message") String message,
                               HttpSession session) {
        int result = orderService.submit(getUid(sessionId), aid, bid, pid, date, time_start, time_end, message);
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
    public BaseResponse count(@RequestParam(value = "sessionId") String sessionId) {
        return BaseResponse.ok(orderService.countByUid(getUid(sessionId)));
    }


    @RequestMapping(value = "/orderCount", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse ordercount(@RequestParam(value = "sessionId") String sessionId) {
        return BaseResponse.ok(orderService.orderCount(getUid(sessionId)));
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse cancel(@RequestParam(value = "sessionId") String sessionId,
                               @RequestParam("oid") int oid) {
        return BaseResponse.ok(orderService.cancel(getUid(sessionId), oid));
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse pay(@RequestParam(value = "sessionId") String sessionId,
                            @RequestParam("oid") int oid,
                            HttpServletRequest request) throws IllegalAccessException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, KeyStoreException, IOException {
        OrderReturnInfo result = orderService.pay(getUid(sessionId), oid, getIpAddr(request));
        return BaseResponse.ok(result);
    }

    /**
     * 获取访问者IP
     * <p>
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * <p>
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     *
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
}
