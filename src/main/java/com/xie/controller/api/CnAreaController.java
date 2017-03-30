package com.xie.controller.api;

import com.xie.response.BaseResponse;
import com.xie.service.CnAreaService;
import com.xie.utils.MallConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-30 上午9:15
 */
@Controller
@RequestMapping("cnArea")
public class CnAreaController extends BaseController {

    @Autowired
    private CnAreaService cnAreaService;


    /**
     * 获取根目录
     *
     * @return
     */
    @RequestMapping(value = "/getByLevel", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getByLevel(@RequestParam(value = "level", required = false, defaultValue = "0") int level) {
        return BaseResponse.ok(cnAreaService.getByLevel(level));
    }

    /**
     * 获取根目录
     *
     * @return
     */
    @RequestMapping(value = "/cnAreaRoot", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse cnAreaRoot() {
        return BaseResponse.ok(cnAreaService.getByName(MallConstants.CNAREA_ROOT));
    }

    /**
     * 获取子区域
     *
     * @param pid
     * @return
     */
    @RequestMapping(value = "/getByPid", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getByPid(@RequestParam(value = "pid") int pid) {
        return BaseResponse.ok(cnAreaService.getByPid(pid));
    }

}
