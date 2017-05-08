package com.xie.controller.api;

import com.xie.bean.Order;
import com.xie.pay.common.StreamUtil;
import com.xie.response.BaseResponse;
import com.xie.response.OrderCheckDto;
import com.xie.service.OrderService;
import com.xie.utils.MallConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xie on 16/11/24.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {

    protected final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 后台管理
     *
     * @param type
     * @param created_at_start
     * @param created_at_end
     * @param time_start
     * @param time_end
     * @param pageNum
     * @param pageSize
     * @return
     */
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

    /**
     * 后台管理
     *
     * @param uid
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getByUid", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    BaseResponse getByUid(@RequestParam("uid") int uid,
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
    BaseResponse list(
            @RequestParam(value = "type") int type,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(orderService.getByType(getUid(), type, pageNum, pageSize));
    }

    /**
     * 后台管理
     *
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse listAll(@RequestParam(value = "type") int type,
                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(orderService.getAllByType(type, pageNum, pageSize));
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
    public BaseResponse check() {
        OrderCheckDto orderCheckDto = orderService.check(getUid());
        return BaseResponse.ok(orderCheckDto);
    }


    @RequestMapping(value = "/orderMore", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse orderMore(
            @RequestParam("oid") int oid) {
        int result = orderService.orderMore(getUid(), oid);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse submit(
            @RequestParam(value = "point", required = false, defaultValue = "0") int point,
            @RequestParam("aid") int aid,
            @RequestParam("bid") int bid,
            @RequestParam("pid") int pid,
            @RequestParam("date") Date date,
            @RequestParam("time_start") Date time_start,
            @RequestParam("time_end") Date time_end,
            @RequestParam(value = "message", required = false, defaultValue = "") String message) {
        int result = orderService.submit(getUid(), point, aid, bid, pid, date, time_start, time_end, message);
        if (result == MallConstants.ERROR_POINT_NOT_ENOUGH) {
            return BaseResponse.fail("积分不足");
        }
        if (result == MallConstants.ERROR_LT_ORDER_MIN_MONEY) {
            return BaseResponse.fail("订单金额低于起送金额");
        }
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
    public BaseResponse count() {
        return BaseResponse.ok(orderService.countByUid(getUid()));
    }


    @RequestMapping(value = "/orderCount", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse ordercount() {
        return BaseResponse.ok(orderService.orderCount(getUid()));
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse cancel(@RequestParam("oid") int oid) {
        return BaseResponse.ok(orderService.cancel(getUid(), oid));
    }

    /**
     * 后台管理
     * @param oid
     * @return
     */
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse cancelOrder(@RequestParam("oid") int oid) {
        int result = orderService.cancelOrder(oid);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    /**
     * 后台管理
     *
     * @param oid
     * @return
     */
    @RequestMapping(value = "/packageOrder", method = RequestMethod.PUT)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse packageOrder(@RequestParam("oid") int oid,
                                     @RequestParam("package_status") int package_status) {
        int result = orderService.packageOrder(oid, package_status);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    /**
     * 后台管理
     *
     * @param oid
     * @return
     */
    @RequestMapping(value = "/sendOrder", method = RequestMethod.PUT)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse sendOrder(@RequestParam("oid") int oid,
                                  @RequestParam("sending_status") int sending_status) {
        int result = orderService.sendOrder(oid, sending_status);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/payResult", method = RequestMethod.GET)
    @ResponseBody
    public String payResult(HttpServletRequest request) throws IOException {
        String reqParams = StreamUtil.read(request.getInputStream());
        logger.info("-------支付结果:" + reqParams);
        StringBuffer sb = new StringBuffer("<xml><return_code>SUCCESS</return_code><return_msg>OK</return_msg></xml>");
        return sb.toString();
    }

}
